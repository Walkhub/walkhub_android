package com.semicolon.walkhub.ui.measure

import android.net.Uri
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityFinishMeasureBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.toRealPath
import com.semicolon.walkhub.viewmodel.measure.FinishMeasureViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker
import id.zelory.compressor.Compressor
import kotlinx.coroutines.launch
import java.io.File

@AndroidEntryPoint
class FinishMeasureActivity :
    BaseActivity<ActivityFinishMeasureBinding>(R.layout.activity_finish_measure) {

    private val viewModel: FinishMeasureViewModel by viewModels()

    override fun initView() {
        fetchImageFromImagePicker()
        setResult()
        setButtons()
        observeEvents()
    }

    private fun setResult() {
        val isDistance = intent.getBooleanExtra("isDistance", false)
        binding.isDistance = isDistance

        val distance = intent.getDoubleExtra("distance", 0.0)
        val walk = intent.getIntExtra("walk", 0)
        val unit = if (isDistance) "km" else "걸음"

        val goal = intent.getIntExtra("goal", 0)
        binding.goal = goal

        val percentage = intent.getIntExtra("percentage", 0)
        binding.finishMeasuringRemainPb.progress = percentage

        val remain = if (isDistance) distance.toInt() else walk
        val remainText = "$remain$unit"
        binding.finishMeasuringRemainTv.text = remainText

        val other = if (isDistance) walk else distance
        binding.finishMeasuringOtherValueTv.text = other.toString()

        val kiloCalorie = intent.getFloatExtra("kiloCalorie", 0f)
        binding.finishMeasuringCalorieTv.text = kiloCalorie.toString().substring(0..2)

        binding.finishMeasuringSpeedTv.text = "0"

        val hour = intent.getIntExtra("hour", 0)
        binding.finishMeasuringHourTv.text = hour.toString()

        val minute = intent.getIntExtra("minute", 0)
        binding.finishMeasuringMinuteTv.text = minute.toString()
    }

    private fun setButtons() {
        binding.run {
            finishMeasuringBackBtn.setOnClickListener {
                finish()
            }

            finishMeasuringCompleteBtn.setOnClickListener {
                viewModel.finishMeasure()
            }
        }
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {
                    when (it) {
                        FinishMeasureViewModel.Event.FailFinish -> {
                            showShortToast("운동측정을 종료하지 못하였습니다")
                        }

                        FinishMeasureViewModel.Event.SuccessFinish -> {
                            showShortToast("운동측정을 종료하였습니다")
                            finish()
                        }

                        FinishMeasureViewModel.Event.ImagePathIsBlank -> {
                            showShortToast("측정종료 사진을 찍어주세요")
                            fetchImageFromImagePicker()
                        }
                    }
                }
            }
        }
    }

    private fun fetchImageFromImagePicker() {
        val requestPhotoComment = "인증사진을 찍어주세요"
        showShortToast(requestPhotoComment)
        TedImagePicker.with(this).start { uri ->
            binding.finishMeasuringIv.setImageURI(uri)
            setImageFile(uri)
        }
    }

    private fun setImageFile(uri: Uri) {
        val path = uri.toRealPath(this@FinishMeasureActivity)
        val originalFile = File(path)
        lifecycleScope.launch {
            val compressedFile = Compressor.compress(this@FinishMeasureActivity, originalFile)
            viewModel.setImageFile(compressedFile)
        }
    }
}