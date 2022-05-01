package com.semicolon.walkhub.ui.measure

import androidx.activity.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityFinishMeasureBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.viewmodel.measure.FinishMeasureViewModel

class FinishMeasureActivity :
    BaseActivity<ActivityFinishMeasureBinding>(R.layout.activity_finish_measure) {

    private val viewModel: FinishMeasureViewModel by viewModels()

    override fun initView() {

    }

    private fun fetchImageFromImagePicker() {

    }
}