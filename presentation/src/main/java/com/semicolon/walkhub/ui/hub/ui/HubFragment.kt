package com.semicolon.walkhub.ui.hub.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enums.DateType
import com.semicolon.domain.enums.MoreDateType
import com.semicolon.walkhub.viewmodel.hub.HubMainViewModel.Event
import com.semicolon.walkhub.R
import com.semicolon.walkhub.customview.Dropdown
import com.semicolon.walkhub.customview.MenuDirection
import com.semicolon.walkhub.databinding.FragmentHubBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubSchoolRankRvAdapter
import com.semicolon.walkhub.util.HubIntentKey
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.ui.hub.model.MySchoolRankData
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.hub.HubMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubFragment @Inject constructor(
) : BaseFragment<FragmentHubBinding>(
    R.layout.fragment_hub
) {

    private val vm: HubMainViewModel by viewModels()

    private var schoolId = 0
    private var moreDateType = MoreDateType.WEEK

    private var schoolRvData = arrayListOf<HubSchoolRankData.OtherSchool>()

    private lateinit var mAdapter: HubSchoolRankRvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchSchoolRank(DateType.WEEK)
        vm.fetchMySchool()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: Event) = when (event) {
        is Event.FetchSchoolRank -> {
            setSchoolRank(event.hubSchoolRankData.schoolList)
        }

        is Event.NullPoint -> {
            showShortToast("데이터가 없습니다.")
        }

        is Event.Unknown -> {
            showShortToast("알 수 없는 오류가 발생하였습니다.")
        }

        is Event.NoInternet -> {
            showShortToast("인터넷에 연결되어 있지 않습니다.")
        }

        is Event.FetchMyRank -> {
            setMySchool(event.mySchoolRankData)
        }
    }

    override fun initView() {
        setAdapter()
        initDropDown()

        binding.clMySchool.setOnClickListener {
            val intent = Intent(context, HubSchoolActivity::class.java).apply {
                putExtra(HubIntentKey.SCHOOL_ID.key, schoolId)
                putExtra(HubIntentKey.SCHOOL_TYPE.key, true)
                putExtra(HubIntentKey.SCHOOL_NAME.key, binding.tvMySchoolName.text)
            }
            startActivity(intent)
        }

        binding.etSearchSchool.setOnClickListener {
            val intent = Intent(context, HubSearchSchoolActivity::class.java).apply {
                putExtra(HubIntentKey.SCHOOL_DATE_TYPE.key, moreDateType.toString())
            }
            startActivity(intent)
        }
    }

    private fun initDropDown() {
        binding.cvWeekDropDown.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                Dropdown(
                    menuDirection = MenuDirection.LEFT,
                    items = arrayOf(
                        stringResource(id = R.string.last_week),
                        stringResource(id = R.string.last_month)
                    ),
                    defaultItemIndex = 0,
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

        binding.rvHubRank.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    private fun setMySchool(school: MySchoolRankData) {
        schoolId = school.schoolId
        binding.ivMySchool.loadCircleFromUrl(school.logoImageUrl)
        binding.tvMySchoolName.text = school.name
        val tvMySchoolInfo =
            if (school.grade == null || school.classNum == null) "현재 소속 중인 반이 없어요."
            else "${school.grade}학년 ${school.classNum}반"
        binding.tvMySchoolInfo.text = tvMySchoolInfo
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSchoolRank(school: List<HubSchoolRankData.OtherSchool>) {
        schoolRvData.clear()
        for (element in school) {
            schoolRvData.add(element)
        }
        binding.rvHubRank.adapter?.notifyDataSetChanged()
    }
}