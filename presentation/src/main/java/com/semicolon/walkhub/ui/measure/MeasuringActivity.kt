package com.semicolon.walkhub.ui.measure

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.semicolon.domain.enums.GoalType
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

    private var isDistance = true
    private var firstValue = -10
    private var secondValue = -10

    override fun initView() {
        binding.vm = viewModel
        setToDefaultState()
        fetchIntentValue()
        if (firstValue.didNotSetGoalFromHome()) {
            fetchMeasuringGoal()
        } else {
            countDownToStartMeasure()
            fetchGoalFromHome()
        }
        observeState()
        observeEvent()
        viewModel.receiveCheering()
    }

    private fun fetchIntentValue() {
        isDistance = intent.getBooleanExtra("isDistance", true)
        firstValue = intent.getIntExtra("firstNumber", -10)
        secondValue = intent.getIntExtra("secondNumber", -10)
    }

    private fun fetchGoalFromHome() {
        if (isDistance) {
            val goal = if (secondValue != 0) firstValue + 1 else firstValue
            setGoalIsForDistance(goal.toKilometer())
        } else {
            val goal = firstValue * 1000 + secondValue * 100
            setGoalIsForWalkCount(goal)
        }
    }

    private fun Int.toKilometer(): Int = this * 1000

    private fun Int.didNotSetGoalFromHome(): Boolean =
        this == -10

    private fun fetchMeasuringGoal() {
        viewModel.fetchMeasuringGoal()
    }

    private fun setGoalIsForDistance(goalDistance: Int) {
        viewModel.run {
            setDistanceGoal(goalDistance)
            startMeasureExercise()
        }
    }

    private fun setGoalIsForWalkCount(goalWalkCount: Int) {
        viewModel.run {
            setWalkCountGoal(goalWalkCount)
            startMeasureExercise()
        }
    }

    private fun setGoalViewIsDistance(goal: Int) {
        val goalText = "/$goal km"
        binding.run {
            measuringGoalTv.text = goalText
            isDistance = true
        }
    }

    private fun setGoalViewIsWalkCount(goal: Int) {
        val goalText = "/$goal 걸음"
        binding.run {
            measuringGoalTv.text = goalText
            isDistance = false
        }
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
            goal.observe(this@MeasuringActivity) {
                isDistance = it.goalType == GoalType.DISTANCE
            }
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
                    else -> {}
                }
            }
            time.observe(this@MeasuringActivity) {
                binding.run {
                    measuringHourTv.text = it.hour.toString()
                    measuringMinuteTv.text = it.minute.toString()
                }
            }
            calorie.observe(this@MeasuringActivity) {
                binding.measuringCalorieTv.text = it.toString().substring(0..2)
            }
            speed.observe(this@MeasuringActivity) {
                binding.measuringSpeedTv.text = it.toString().substring(0..2)
            }
            percentage.observe(this@MeasuringActivity) {
                binding.measuringRemainPb.progress = it
            }
            goal.observe(this@MeasuringActivity) {
                if (it.goalType == GoalType.DISTANCE) {
                    setGoalViewIsDistance(it.goal)
                } else if (it.goalType == GoalType.WALK_COUNT) {
                    setGoalViewIsWalkCount(it.goal)
                }
            }
            distanceAsKiloMeter.observe(this@MeasuringActivity) {
                val distance = it.toString().substring(0..2)
                val distanceText = "$distance km"
                if (isDistance) {
                    binding.measuringRemainTv.text = distanceText
                } else {
                    binding.measuringOtherValueTv.text = distanceText
                }

                setPercentage()
            }
            walkCount.observe(this@MeasuringActivity) {
                val walkCount = "$it 걸음"
                if (isDistance) {
                    binding.measuringOtherValueTv.text = walkCount
                } else {
                    binding.measuringRemainTv.text = walkCount
                }

                setPercentage()
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

            measuringFinishBtn.setOnClickListener {
                viewModel.fetchFinishPhoto()
            }
            measuringBackBtn.setOnClickListener {
                finish()
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.event.collect {
                    when (it) {
                        MeasureViewModel.Event.StartFetchPhoto -> {
                            startFinishActivity()
                        }
                        MeasureViewModel.Event.FailStartMeasure -> {
                            showShortToast("운동측정을 시작할 수 없습니다")
                            finish()
                        }
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

    private fun startFinishActivity() {
        val intent = Intent(this, FinishMeasureActivity::class.java).apply {
            putExtra("isDistance", isDistance)
            putExtra("percentage", viewModel.percentage.value)

            putExtra("goal", viewModel.goal.value?.goal)

            putExtra("distance", viewModel.distanceAsKiloMeter.value)
            putExtra("walk", viewModel.walkCount.value)

            putExtra("kiloCalorie", viewModel.calorie.value)
            putExtra("hour", viewModel.time.value?.hour)
            putExtra("minute", viewModel.time.value?.minute)
        }
        startActivity(intent)

        finish()
    }
}