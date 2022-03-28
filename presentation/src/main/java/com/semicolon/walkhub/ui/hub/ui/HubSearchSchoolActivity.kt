package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enums.DateType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHubSearchSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchSchoolRvAdapter
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.util.onTextChanged
import com.semicolon.walkhub.viewmodel.register.SearchSchoolViewModel

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubSearchSchoolActivity : BaseActivity<ActivityHubSearchSchoolBinding> (
    R.layout.activity_hub_search_school
) {

    private val vm: SearchSchoolViewModel by viewModels()

    private val schoolRvData = arrayListOf<SearchSchoolData.SchoolInfo>()

    private lateinit var mAdapter: HubSearchSchoolRvAdapter

    private var dateType = DateType.WEEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dateType = transferDateType(intent.getStringExtra("dateType")!!)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    private fun transferDateType(dateType: String) = when(dateType) {
        "MONTH" -> DateType.MONTH
        else -> DateType.WEEK
    }

    private fun handleEvent(event: SearchSchoolViewModel.Event) = when (event) {
        is SearchSchoolViewModel.Event.SearchSchool -> {
            setSchoolData(event.searchSchoolData)
        }
        is SearchSchoolViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        else -> {}
    }

    override fun initView() {
        setAdapter()
        setTextChanged()

        binding.ibBack.setOnClickListener {
            finish()
        }
    }

    private fun setSchoolData(list: SearchSchoolData) {
        schoolRvData.clear()

        for(i: Int in list.schoolList.indices) {
            schoolRvData.add(list.schoolList.get(i))
        }

        binding.rvSchoolRank.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        mAdapter = HubSearchSchoolRvAdapter(schoolRvData)

        binding.rvSchoolRank.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun setTextChanged() {
        binding.etSearch.onTextChanged { s, start, before, count ->
            vm.searchSchoolDebounce(s.toString())
        }
    }
}