package com.semicolon.walkhub.ui.measure

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityMeasuringBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.util.toRealPath
import com.semicolon.walkhub.viewmodel.measure.MeasureViewModel
import dagger.hilt.android.AndroidEntryPoint
import gun0912.tedimagepicker.builder.TedImagePicker
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MeasuringActivity : BaseActivity<ActivityMeasuringBinding>(R.layout.activity_measuring) {

    private val viewModel: MeasureViewModel by viewModels()

    private var isDistance = true
    private var firstValue = -10
    private var secondValue = -10

    override fun initView() {
        binding.vm = viewModel
        setToDefaultState()
        fetchIntentValue()
        if (firstValue.didNotSetGoalFromHome()) {
            viewModel.fetchMeasuredData()
        } else {
            countDownToStartMeasure()
        }
        observeState()
        observeEvent()
        fetchGoalFromHome()
        viewModel.receiveCheering()
    }

    private fun fetchIntentValue() {
        isDistance = intent.getBooleanExtra("isDistance", true)
        firstValue = intent.getIntExtra("firstNumber", -10)
        secondValue = intent.getIntExtra("secondNumber", -10)
    }

    private fun fetchGoalFromHome() {
        if (firstValue.didNotSetGoalFromHome()) {
            fetchMeasuringGoal()
        } else {
            if (isDistance) {
                val goal = if (secondValue != 0) firstValue + 1 else firstValue
                setGoalIsForDistance(goal)
            } else {
                val goal = firstValue * 1000 + secondValue * 100
                setGoalIsForWalkCount(goal)
            }
        }
    }

    private fun Int.didNotSetGoalFromHome(): Boolean =
        this == -10

    private fun fetchMeasuringGoal() {
        viewModel.fetchMeasuringGoal()
    }

    private fun setGoalIsForDistance(goalDistance: Int) {
        val goalText = "/$goalDistance km"
        binding.run {
            measuringGoalTv.text = goalText
            isDistance = true
        }
        viewModel.setDistanceGoal(goalDistance)
        viewModel.startMeasureExercise()
    }

    private fun setGoalIsForWalkCount(goalWalkCount: Int) {
        val goalText = "/$goalWalkCount 걸음"
        binding.run {
            measuringGoalTv.text = goalText
            isDistance = false
        }
        viewModel.setWalkCountGoal(goalWalkCount)
        viewModel.startMeasureExercise()
    }

    private fun countDownToStartMeasure() {
        binding.measuringReadyCl.visibility = View.VISIBLE
        var count = 3
        binding.count = count.toString()
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                binding.count = count.toString()
                count -= 1
                if (count < 0) {
                    timer.cancel()
                    binding.measuringReadyCl.visibility = View.INVISIBLE
                }
            }
        }, 0, 1000)
    }

    private fun observeState() {
        viewModel.run {
            measuringState.observe(this@MeasuringActivity) { state ->
                when (state) {
                    MeasureViewModel.MeasureState.ONGOING -> {
                        setToDefaultState()
                    }
                    MeasureViewModel.MeasureState.PAUSED -> {
                        setToPausedState()
                    }
                    MeasureViewModel.MeasureState.LOCK -> {
                        setToLockState()
                    }
                }
            }
            time.observe(this@MeasuringActivity) {
                binding.run {
                    measuringHourTv.text = it.hour.toString()
                    measuringMinuteTv.text = it.minute.toString()
                }
            }
            walkCount.observe(this@MeasuringActivity) {
                binding.measuringWalkTv.text = it.toString()
            }
            calorie.observe(this@MeasuringActivity) {
                binding.measuringCalorieTv.text = it.toString()
            }
            speed.observe(this@MeasuringActivity) {
                binding.measuringSpeedTv.text = it.toString()
            }
            percentage.observe(this@MeasuringActivity) {
                binding.measuringRemainPb.progress = it
            }
        }
    }

    private fun observeEvent() {
        binding.run {
            measuringPauseBtn.setOnClickListener {
                if (viewModel.measuringState.value == MeasureViewModel.MeasureState.LOCK) {
                    viewModel.unLockMeasureExercise()
                } else {
                    viewModel.pauseMeasureExercise()
                }
            }
            measuringLockBtn.setOnClickListener {
                viewModel.lockMeasureExercise()
            }
            measuringResumeBtn.setOnClickListener {
                viewModel.resumeMeasureExercise()
            }

            val requestPhotoComment = "인증사진을 찍어주세요"
            measuringFinishBtn.setOnClickListener {
                viewModel.fetchFinishPhoto()
                showShortToast(requestPhotoComment)
            }
            measuringBackBtn.setOnClickListener {
                finish()
            }
        }
        lifecycleScope.launch {
            viewModel.event.collect {
                when (it) {
                    MeasureViewModel.Event.FinishActivity -> {
                        finish()
                    }
                    MeasureViewModel.Event.FinishMeasure -> {
                        viewModel.finishMeasureExercise()
                    }
                    MeasureViewModel.Event.RequestPhoto -> {
                        showShortToast("측정 왈료 사진을 등록해주세요")
                    }
                    MeasureViewModel.Event.StartFetchPhoto -> {
                        fetchDoneImage()
                    }
                    MeasureViewModel.Event.StartMeasureError -> {
                        showShortToast("운동측정을 시작할 수 없습니다.")
                        finish()
                    }
                }
            }
        }
    }

    private fun setToDefaultState() {
        binding.run {
            measuringPauseBtn.setImageResource(R.drawable.ic_measuring_pause)
            measuringPauseBtn.visibility = View.VISIBLE
            measuringLockBtn.visibility = View.VISIBLE

            pausedView.visibility = View.INVISIBLE

            measuringResumeBtn.visibility = View.INVISIBLE
            measuringFinishBtn.visibility = View.INVISIBLE
        }
    }

    private fun setToLockState() {
        binding.run {
            measuringPauseBtn.setImageResource(R.drawable.ic_measuring_un_lock)
            measuringPauseBtn.visibility = View.VISIBLE

            pausedView.visibility = View.INVISIBLE

            measuringLockBtn.visibility = View.INVISIBLE
            measuringResumeBtn.visibility = View.INVISIBLE
            measuringFinishBtn.visibility = View.INVISIBLE
        }
    }

    private fun setToPausedState() {
        binding.run {
            measuringResumeBtn.visibility = View.VISIBLE
            measuringFinishBtn.visibility = View.VISIBLE

            pausedView.visibility = View.VISIBLE

            measuringLockBtn.visibility = View.INVISIBLE
            measuringPauseBtn.visibility = View.INVISIBLE
        }
    }

    private fun fetchDoneImage() {
        TedImagePicker.with(this).start {
            viewModel.setImageUri(it.toRealPath(this))
        }
    }
}