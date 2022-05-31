package com.semicolon.walkhub.ui.hub.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.domain.enums.RankScope
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.Dropdown
import com.semicolon.walkhub.customview.MenuDirection
import com.semicolon.walkhub.customview.ToggleSwitch
import com.semicolon.walkhub.databinding.FragmentHubRankBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.util.HubIntentKey
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubRankFragment : BaseFragment<FragmentHubRankBinding>(
    R.layout.fragment_hub_rank
) {

    private val vm: HubUserViewModel by viewModels()

    companion object {
        var dateType = MoreDateType.WEEK
        var rankScope = RankScope.SCHOOL
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun fetchSchoolUserRank() {
        val schoolType = activity?.intent?.getBooleanExtra(HubIntentKey.SCHOOL_TYPE.key, HubIntentKey.SCHOOL_TYPE.default as Boolean)!!
        val schoolId = activity?.intent?.getIntExtra(HubIntentKey.SCHOOL_ID.key, HubIntentKey.SCHOOL_ID.default as Int)!!

        if (schoolType) {
            vm.fetchMySchoolUserRank(rankScope, dateType)
        } else {
            vm.fetchSchoolUserRank(schoolId, dateType)
        }
    }

    private fun handleEvent(event: HubUserViewModel.Event) = when (event) {
        is HubUserViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.vm = vm

        initSpinner()
        initDropDown()
        setScrollListener()

        fetchSchoolUserRank()

        binding.tvJoinClass.setOnClickListener {
            startActivity(Intent(activity, SignUpClassActivity::class.java))
        }
    }

    private fun setScrollListener() {

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
                    items = arrayOf(
                        stringResource(id = R.string.yesterday),
                        stringResource(id = R.string.last_week),
                        stringResource(id = R.string.last_month)
                    ),
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