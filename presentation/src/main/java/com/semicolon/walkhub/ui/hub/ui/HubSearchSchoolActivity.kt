package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enums.DateType
import com.semicolon.domain.param.user.FetchSchoolRankAndSearchParam
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHubSearchSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchSchoolRvAdapter
import com.semicolon.walkhub.util.HubIntentKey
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.util.onTextChanged
import com.semicolon.walkhub.viewmodel.hub.HubSearchSchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubSearchSchoolActivity : BaseActivity<ActivityHubSearchSchoolBinding>(
    R.layout.activity_hub_search_school
) {

    private val vm: HubSearchSchoolViewModel by viewModels()

    private val schoolRvData = arrayListOf<SearchSchoolData.SchoolInfo>()

    private lateinit var mAdapter: HubSearchSchoolRvAdapter

    private var dateType = DateType.WEEK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dateType = transferDateType(intent.getStringExtra(HubIntentKey.SCHOOL_DATE_TYPE.key)!!)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

    }

    private fun transferDateType(dateType: String) = when (dateType) {
        DateType.MONTH.toString() -> DateType.MONTH
        else -> DateType.WEEK
    }

    private fun handleEvent(event: HubSearchSchoolViewModel.Event) = when (event) {
        is HubSearchSchoolViewModel.Event.SearchSchool -> {
            setSchoolData(event.searchSchoolData)
        }
        is HubSearchSchoolViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
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

        for (i: Int in list.schoolList.indices) {
            schoolRvData.add(list.schoolList[i])
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
        binding.etSearch.onTextChanged { s, _, _, _ ->
            vm.searchSchoolDebounce(
                FetchSchoolRankAndSearchParam(
                    s.toString(),
                    dateType.toString()
                )
            )
        }
    }
}