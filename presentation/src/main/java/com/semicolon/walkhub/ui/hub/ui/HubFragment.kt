package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.walkhub.viewmodel.HubMainViewModel.Event
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHubBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubSchoolRankRvAdapter
import com.semicolon.walkhub.ui.hub.model.HubSchoolRank
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.HubMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubFragment @Inject constructor(
): BaseFragment<FragmentHubBinding>(
    R.layout.fragment_hub
) {

    private val vm: HubMainViewModel by viewModels()

    private var schoolRvData = arrayListOf<HubSchoolRank.OtherSchool>()
    private lateinit var mAdapter: HubSchoolRankRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchSchoolRank()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.FetchSchoolRank -> {
            setMySchool(event.hubSchoolRank.my_school_rank)
            setSchoolRank(event.hubSchoolRank.school_list)
        }
    }

    override fun initView() {

        setAdapter()
        setSpinner()
    }

    private fun setSpinner() {

        ArrayAdapter.createFromResource(
            context!!,
            R.array.hubMainSpinnerItem,
            R.layout.item_hub_main_spinner
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spHubMain.adapter = adapter
        }

        binding.spHubMain.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                when (p2) {
                    0 -> Toast.makeText(context, "지난주", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(context, "지난달", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun setAdapter() {

        mAdapter = HubSchoolRankRvAdapter(schoolRvData)

        binding.rvHubRank.layoutManager = LinearLayoutManager(context)
        binding.rvHubRank.setHasFixedSize(true)
        binding.rvHubRank.adapter = mAdapter
    }

    private fun setMySchool(school: HubSchoolRank.MySchool) {

        binding.ivMySchool.loadCircleFromUrl(school.logo_image_url)
        binding.tvMySchoolName.text = school.name
        binding.tvMySchoolInfo.text = "${school.grade} 학년 ${school.class_num} 반"
    }

    private fun setSchoolRank(school: List<HubSchoolRank.OtherSchool>) {
        for(i: Int in 0..school.size-1) {
            schoolRvData.add(school[i])
        }

        binding.rvHubRank.adapter?.notifyDataSetChanged()
    }
}