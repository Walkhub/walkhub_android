package com.semicolon.walkhub.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nms_android_v1.base.BaseFragment
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentRecordMeasurementBinding
import com.semicolon.walkhub.ui.MainActivity

class RecordMeasurementFragment : BaseFragment<FragmentRecordMeasurementBinding>(
    R.layout.fragment_home
) {
    override fun initView() {

         var mainActivity:MainActivity

        mainActivity = context as MainActivity

            binding.recyclerview.layoutManager =
                LinearLayoutManager( mainActivity ).also { it.orientation =
                LinearLayoutManager.HORIZONTAL}

    }

    override fun observeEvent() {
    }
}