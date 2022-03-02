package com.semicolon.walkhub.ui.measure

import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityMeasureHomeBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.measure.MeasureHomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MeasureHomeActivity :
    BaseActivity<ActivityMeasureHomeBinding>(R.layout.activity_measure_home) {

    private val viewModel: MeasureHomeViewModel by viewModels()

    private val walkCountNumberPickerValues =
        arrayOf("000", "100", "200", "300", "400", "500", "600", "700", "800", "900")

    private val distanceCountNumberPickerValues =
        IntArray(100) { it }.map { if (it < 10) "0$it" else it.toString() }.toTypedArray()

    override fun initView() {
        binding.vm = viewModel
        lifecycleScope.launch {
            viewModel.isDistance.collect {
                setMeasurementUi(it)
            }
        }

    }

    private fun setMeasurementUi(isDistance: Boolean) {
        binding.isDistance = isDistance
        binding.run {

            measureFirstNp.run {
                maxValue = 99
                minValue = 0
                value = if (isDistance) 2 else 10
            }

            if (isDistance) {
                measureSecondNp.run {
                    displayedValues = distanceCountNumberPickerValues
                    maxValue = distanceCountNumberPickerValues.size - 1
                }
            } else {
                measureSecondNp.run {
                    maxValue = walkCountNumberPickerValues.size - 1
                    displayedValues = walkCountNumberPickerValues
                }
            }

            measureSecondNp.run {
                minValue = 0
                value = 0
            }

            kmTv.text = if (isDistance) "km" else "걸음"
            commaOrColonTv.text = if (isDistance) "." else ","
        }
    }
}