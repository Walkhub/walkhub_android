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
import com.semicolon.domain.enum.RankScope
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.Dropdown
import com.semicolon.walkhub.customview.MenuDirection
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.FragmentHubRankBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubUserRvAdapter
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.ui.hub.model.MySchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.ui.hub.model.toRvData
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.HubSearchUserViewModel
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubRankFragment : BaseFragment<FragmentHubRankBinding>(
    R.layout.fragment_hub_rank
) {

    private val vm: HubUserViewModel by viewModels()

    private val userRvData = arrayListOf<UserRankRvData>()

    private var dateType = DateType.WEEK
    private var rankScope = RankScope.ALL

    private lateinit var mAdapter: HubUserRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fetchSchoolUserRank()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun fetchSchoolUserRank() {

        val schoolType = activity?.intent?.getBooleanExtra("type", true)!!

        if (schoolType) {
            vm.fetchMySchoolUserRank(dateType, rankScope)
        } else {
            vm.fetchSchoolUserRank(dateType, rankScope)
        }
    }

    private fun handleEvent(event: HubUserViewModel.Event) = when (event) {
        is HubUserViewModel.Event.FetchMySchoolUserRank -> {
            setMyRank(event.mySchoolUserRankData.myRanking)
            setUserRvData(event.mySchoolUserRankData.rankingList.map { it.toRvData() })
        }
        is HubUserViewModel.Event.FetchOtherSchoolUserRank -> {
            setUserRvData(event.userRankData.rankList.map { it.toRvData() })
        }
    }

    private fun setUserRvData(school: List<UserRankRvData>) {

        userRvData.clear()

        for (i: Int in 0..school.size - 1) {
            userRvData.add(school[i])
        }

        binding.rvRank.adapter?.notifyDataSetChanged()
    }

    override fun initView() {

        initSpinner()
        initDropDown()
        setAdapter()
    }

    private fun setMyRank(data: MySchoolUserRankData.Ranking) {

        binding.clMyRank.visible()

        binding.ivMyProfile.loadCircleFromUrl(data.profileImageUrl)
        binding.tvMyName.text = data.name
        binding.tvMyWalkCount.text = "${data.walkCount} 걸음"
        binding.tvMyRank.text = "${data.ranking} 등"
    }

    private fun setAdapter() {

        mAdapter = HubUserRvAdapter(userRvData)

        binding.rvRank.layoutManager = LinearLayoutManager(context)
        binding.rvRank.setHasFixedSize(true)
        binding.rvRank.adapter = mAdapter
    }


    private fun initSpinner() {

        binding.cvSwitch.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = { setRankScope(RankScope.ALL) },
                    onToggleOff = { setRankScope(RankScope.CLASS) }
                )
            }
        }
    }

    private fun initDropDown() {
        binding.cvWeekDropDown.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Dropdown(
                    items = arrayOf("지난주", "지난달"),
                    defaultItemIndex = 1,
                    menuDirection = MenuDirection.LEFT,
                    onItemSelected = { index, _ -> dropDownItemSelect(index) }
                )
            }
        }
    }

    private fun dropDownItemSelect(index: Int) {
        when (index) {
            0 -> setDateType(DateType.WEEK)
            1 -> setDateType(DateType.MONTH)
        }
    }

    private fun setRankScope(rankScope: RankScope) {

        this.rankScope = rankScope
        fetchSchoolUserRank()
    }

    private fun setDateType(dateType: DateType) {

        this.dateType = dateType
        fetchSchoolUserRank()
    }
}