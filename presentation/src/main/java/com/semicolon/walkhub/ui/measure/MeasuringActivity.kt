package com.semicolon.walkhub.ui.measure

import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.semicolon.domain.enums.MeasuringState
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityMeasuringBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.measure.MeasureViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MeasuringActivity : BaseActivity<ActivityMeasuringBinding>(R.layout.activity_measuring) {

    private val viewModel: MeasureViewModel by viewModels()

    override fun initView() {
        setToDefault()
        countDown()
        startMeasureExercise()
        observeState()
        observeEvent()
    }

    private fun startMeasureExercise() {
        val isDistance = intent.getBooleanExtra("isDistance", true)
        val firstValue = intent.getIntExtra("firstNumber", 0)
        val secondValue = intent.getIntExtra("secondNumber",0)

        val goal = if(isDistance) {
            firstValue * 100 + secondValue
        } else {
            firstValue * 1000 + secondValue
        }

        binding.isDistance = isDistance

        viewModel.run {
            startMeasureExercise(goal, isDistance)
            //fetchMeasuredExercise()
            //fetchMeasuredTime()
            //fetchCurrentSpeed()
        }
    }

    private fun countDown() {
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
            measuringState.observe(this@MeasuringActivity) {
                when(it) {
                    MeasureViewModel.MeasureState.ONGOING -> {
                        setToDefault()
                    }
                    MeasureViewModel.MeasureState.PAUSED -> {
                        setToPaused()
                    }
                    MeasureViewModel.MeasureState.LOCK -> {
                        setToLock()
                    }
                }
            }
            lifecycleScope.launch {
                finishMeasuring.collect {
                    TODO("Finish and start 사진찍기")
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
        }
    }

    private fun observeEvent() {
        binding.run {
            measuringPauseBtn.setOnClickListener {
                if (viewModel.measuringState.value == MeasureViewModel.MeasureState.PAUSED) {
                    viewModel.resumeMeasureExercise()
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
            measuringFinishBtn.setOnClickListener {
                viewModel.finishMeasureExercise()
            }
        }

    }
    private fun setToDefault() {
        binding.run {
            measuringPauseBtn.setImageResource(R.drawable.ic_measuring_pause)
            measuringPauseBtn.visibility = View.VISIBLE
            measuringLockBtn.visibility = View.VISIBLE

            measuringResumeBtn.visibility = View.INVISIBLE
            measuringFinishBtn.visibility = View.INVISIBLE
        }
    }

    private fun setToLock() {
        binding.run {
            measuringPauseBtn.setImageResource(R.drawable.ic_measuring_un_lock)
            measuringPauseBtn.visibility = View.VISIBLE

            measuringLockBtn.visibility = View.INVISIBLE
            measuringResumeBtn.visibility = View.INVISIBLE
            measuringFinishBtn.visibility = View.INVISIBLE
        }
    }

    private fun setToPaused() {
        binding.run {
            measuringResumeBtn.visibility = View.VISIBLE
            measuringFinishBtn.visibility = View.VISIBLE

            measuringLockBtn.visibility = View.INVISIBLE
            measuringPauseBtn.visibility = View.VISIBLE
        }
    }
}