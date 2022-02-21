package com.semicolon.walkhub.ui.hub.ui

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.semicolon.domain.enum.DateType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHubRankBinding
import com.semicolon.walkhub.ui.base.BaseFragment

class HubRankFragment : BaseFragment<FragmentHubRankBinding>(
    R.layout.fragment_hub_rank
) {
    override fun initView() {

        setSpinner()
    }

    private fun setSpinner() {

        ArrayAdapter.createFromResource(
            context!!,
            R.array.hubMainSpinnerItem,
            R.layout.item_hub_main_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spRankHub.adapter = adapter
        }

        binding.spRankHub.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> TODO("connect viewModel")
                    1 -> TODO("connect viewModel")
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}