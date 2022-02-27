package com.semicolon.walkhub.ui.hub.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enum.DateType
import com.semicolon.domain.enum.MoreDateType
import com.semicolon.walkhub.viewmodel.hub.HubMainViewModel.Event
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.Dropdown
import com.semicolon.walkhub.customview.MenuDirection
import com.semicolon.walkhub.databinding.FragmentHubBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubSchoolRankRvAdapter
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.hub.HubMainViewModel
import com.semicolon.walkhub.viewmodel.hub.HubSearchSchoolViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubFragment @Inject constructor(
) : BaseFragment<FragmentHubBinding>(
    R.layout.fragment_hub
) {

    private val vm: HubMainViewModel by viewModels()
    private val searchViewModel: HubSearchSchoolViewModel by viewModels()

    private var moreDateType = MoreDateType.WEEK

    private var schoolRvData = arrayListOf<HubSchoolRankData.OtherSchool>()
    private lateinit var mAdapter: HubSchoolRankRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.testLogin()
        vm.fetchSchoolRank(DateType.WEEK)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.FetchSchoolRank -> {
            setMySchool(event.hubSchoolRankData.mySchoolRank)
            setSchoolRank(event.hubSchoolRankData.schoolList)
        }
        is Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {

        setAdapter()
        initDropDown()

        binding.clMySchool.setOnClickListener {
            val intent = Intent(context, HubSchoolActivity::class.java)
            intent.putExtra("type", true)
            intent.putExtra("name", binding.tvMySchoolName.text)
            startActivity(intent)
        }

        binding.etSearchSchool.setOnClickListener {
            val intent = Intent(context, HubSearchSchoolActivity::class.java)
            intent.putExtra("dateType", moreDateType.toString())
            startActivity(intent)
        }
    }

    private fun initDropDown() {
        binding.cvWeekDropDown.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Dropdown(
                    menuDirection = MenuDirection.LEFT,
                    items = arrayOf("지난주", "지난달"),
                    defaultItemIndex = 1,
                    onItemSelected = { index, _ -> dropDownItemSelect(index) }
                )
            }
        }
    }

    private fun dropDownItemSelect(index: Int) {
        when (index) {
            0 -> {
                moreDateType = MoreDateType.WEEK
                vm.fetchSchoolRank(DateType.WEEK)
            }
            1 -> {
                moreDateType = MoreDateType.MONTH
                vm.fetchSchoolRank(DateType.MONTH)
            }
        }
    }

    private fun setAdapter() {

        mAdapter = HubSchoolRankRvAdapter(schoolRvData)

        binding.rvHubRank.layoutManager = LinearLayoutManager(context)
        binding.rvHubRank.setHasFixedSize(true)
        binding.rvHubRank.adapter = mAdapter
    }

    private fun setMySchool(school: HubSchoolRankData.MySchool) {

        binding.ivMySchool.loadCircleFromUrl(school.logoImageUrl)
        binding.tvMySchoolName.text = school.name
        binding.tvMySchoolInfo.text = "${school.grade} 학년 ${school.classNum} 반"
    }

    private fun setSchoolRank(school: List<HubSchoolRankData.OtherSchool>) {
        schoolRvData.clear()

        for (i: Int in 0..school.size - 1) {
            schoolRvData.add(school[i])
        }

        binding.rvHubRank.adapter?.notifyDataSetChanged()
    }
}