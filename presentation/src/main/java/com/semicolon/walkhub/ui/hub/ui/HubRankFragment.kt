package com.semicolon.walkhub.ui.hub.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.Dropdown
import com.semicolon.walkhub.customview.MenuDirection
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.FragmentHubRankBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubUserRvAdapter
import com.semicolon.walkhub.ui.hub.model.MySchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.ui.hub.model.toRvData
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubRankFragment : BaseFragment<FragmentHubRankBinding>(
    R.layout.fragment_hub_rank
) {

    private val vm: HubUserViewModel by viewModels()

    private val userRvData = arrayListOf<UserRankRvData>()

    companion object {
        var dateType = MoreDateType.WEEK
        var rankScope = RankScope.SCHOOL
    }

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
        val schoolType = activity?.intent?.getBooleanExtra("type", false)!!
        val schoolId = activity?.intent?.getIntExtra("schoolId", 0)!!

        if (schoolType) {
            vm.fetchMySchoolUserRank(rankScope, dateType)
        } else {
            vm.fetchSchoolUserRank(schoolId, dateType)
        }
    }

    private fun handleEvent(event: HubUserViewModel.Event) = when (event) {
        is HubUserViewModel.Event.FetchMySchoolUserRank -> {
            event.mySchoolUserRankData.let { setMyRank(it) }
            setUserRvData(event.mySchoolUserRankData.rankingList.map { it.toRvData() })

            if (event.mySchoolUserRankData.isJoinedClass) {
                binding.tvJoinClass.invisible()
            } else {
                binding.tvJoinClass.visible()
            }
        }
        is HubUserViewModel.Event.FetchOtherSchoolUserRank -> {
            setUserRvData(event.userRankData.rankList.map { it.toRvData() })
        }
        is HubUserViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
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

        binding.tvJoinClass.setOnClickListener {
            startActivity(Intent(context, SignUpClassActivity::class.java))
        }
    }

    @Suppress("SetTextI18n")
    private fun setMyRank(data: MySchoolUserRankData) {
        binding.clMyRank.visible()
        data.myRanking?.ranking?.plus(1)

        val topWalkCount =
            if (data.myRanking?.ranking!! <= 1) 0
            else data.rankingList.get(data.myRanking.ranking - 2).walkCount
        val downWalkCount =
            if (data.myRanking.ranking >= data.rankingList.size) 0
            else data.rankingList.get(data.myRanking.ranking).walkCount
        val myWalkCount = data.myRanking.walkCount

        binding.ivMyProfile.loadCircleFromUrl(data.myRanking.profileImageUrl)
        binding.tvMyName.text = data.myRanking.name
        binding.tvMyWalkCount.text = "$myWalkCount 걸음"
        binding.tvMyRank.text = "${data.myRanking.ranking} 등"

        binding.pbMy.progress =
            (myWalkCount.toDouble() / topWalkCount.toDouble() * 100).toInt()
        binding.tvMyRemainWalkCount.text =
            if (topWalkCount == 0) "최고 등수를 달성했어요!" else "다음 등수까지 ${topWalkCount - myWalkCount} 걸음"
        binding.tvNextWalkCount.text =
            if (topWalkCount == 0) "$myWalkCount 걸음" else "$topWalkCount 걸음"
    }

    private fun setAdapter() {
        mAdapter = HubUserRvAdapter(userRvData)

        binding.rvRank.layoutManager = LinearLayoutManager(context)
        binding.rvRank.setHasFixedSize(true)
        binding.rvRank.adapter = mAdapter

        val mScrollViewListener =
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
                if (scrollY == 0) {
                    binding.pbMy.visible()
                    binding.lnTypeSwitch.visible()
                    binding.tvNextWalkCount.visible()
                    binding.tvMyRemainWalkCount.visible()
                    binding.lnTypeSwitch.visible()
                    binding.cvWeekDropDown.visible()
                } else {
                    binding.pbMy.invisible()
                    binding.lnTypeSwitch.invisible()
                    binding.tvNextWalkCount.invisible()
                    binding.tvMyRemainWalkCount.invisible()
                    binding.lnTypeSwitch.invisible()
                    binding.cvWeekDropDown.invisible()
                }
            }

        binding.nsRank.setOnScrollChangeListener(mScrollViewListener)
    }


    private fun initSpinner() {
        binding.cvSwitch.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ToggleSwitch(
                    onToggleOn = { setRankScope(RankScope.SCHOOL) },
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
                    items = arrayOf("어제", "지난주", "지난달"),
                    defaultItemIndex = 1,
                    menuDirection = MenuDirection.LEFT,
                    onItemSelected = { index, _ -> dropDownItemSelect(index) }
                )
            }
        }
    }

    private fun dropDownItemSelect(index: Int) {
        when (index) {
            0 -> setDateType(MoreDateType.DAY)
            1 -> setDateType(MoreDateType.WEEK)
            2 -> setDateType(MoreDateType.MONTH)
        }
    }

    private fun setRankScope(_rankScope: RankScope) {
        rankScope = _rankScope
        fetchSchoolUserRank()
    }

    private fun setDateType(_dateType: MoreDateType) {
        dateType = _dateType
        fetchSchoolUserRank()
    }
}