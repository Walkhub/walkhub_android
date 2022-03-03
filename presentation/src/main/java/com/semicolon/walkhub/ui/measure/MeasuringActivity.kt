package com.semicolon.walkhub.ui.measure

import android.view.View
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityMeasuringBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import java.util.*

class MeasuringActivity : BaseActivity<ActivityMeasuringBinding>(R.layout.activity_measuring) {

    override fun initView() {
        countDown()
    }

    private fun countDown() {
        var count = 3
        binding.count = count.toString()
        Timer().schedule(object : TimerTask() {
            override fun run() {
                binding.count = count.toString()
                count -= 1
                if(count < 0) {
                    cancel()
                    binding.measuringReadyCl.visibility = View.INVISIBLE
                }
            }
        }, 0, 1000)
    }
}