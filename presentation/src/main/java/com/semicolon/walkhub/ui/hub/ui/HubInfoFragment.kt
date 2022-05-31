package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.entity.school.SchoolDetailEntity
import com.semicolon.domain.enums.NoticeType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHubInfoBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubInfoNoticeRvAdapter
import com.semicolon.walkhub.ui.hub.model.HubInfoNoticeRvData
import com.semicolon.walkhub.util.HubIntentKey
import com.semicolon.walkhub.ui.hub.model.toRvData
import com.semicolon.walkhub.util.setRankImage
import com.semicolon.walkhub.viewmodel.hub.HubInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubInfoFragment : BaseFragment<FragmentHubInfoBinding>(
    R.layout.fragment_hub_info
) {

    private val vm: HubInfoViewModel by viewModels()

    private var rvNoticeData = arrayListOf<HubInfoNoticeRvData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val schoolId = activity?.intent?.getIntExtra(HubIntentKey.SCHOOL_ID.key, 0)!!

        vm.fetchNoticeList(NoticeType.SCHOOL)
        vm.fetchSchoolDetail(schoolId)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: HubInfoViewModel.Event) = when (event) {
        is HubInfoViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        is HubInfoViewModel.Event.FetchNoticeList -> {
            setNoticeList(event.noticeList.noticeValueEntity.map { it.toRvData() })
        }
        is HubInfoViewModel.Event.FetchSchoolDetail -> {
            setHubInfo(event.schoolDetail)
        }
    }

    override fun initView() {
        setAdapter()
    }

    private fun setAdapter() {
        val mAdapter = HubInfoNoticeRvAdapter(rvNoticeData)

        binding.rvHubNotice.run {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun setHubInfo(data: SchoolDetailEntity) {
        binding.run {
            data.week.run {
                val weekRankText = "$ranking 등"
                val weekUserCountText = "$totalUserCount 명"
                val weekWalkCountText ="$totalWalkCount 걸음"

                tvWeekDate.text = date
                tvWeekRank.text = weekRankText
                tvWeekUserCount.text = weekUserCountText
                tvWeekWalkCount.text = weekWalkCountText
                ranking?.let { ivWeekRank.setRankImage(it) }
            }
            data.month.run {
                val monthRankText = "$ranking 등"
                val monthUserCountText = "$totalUserCount 명"
                val monthWalkCountText ="$totalWalkCount 걸음"

                tvMonthDate.text = date
                tvMonthRank.text = monthRankText
                tvMonthUserCount.text = monthUserCountText
                tvMonthWalkCount.text = monthWalkCountText
                ranking?.let { ivWeekRank.setRankImage(it) }
            }
        }
    }

    private fun setNoticeList(noticeList: List<HubInfoNoticeRvData>) {
        for(element in noticeList) {
            rvNoticeData.add(element)
        }
        binding.rvHubNotice.adapter?.notifyDataSetChanged()
    }
}