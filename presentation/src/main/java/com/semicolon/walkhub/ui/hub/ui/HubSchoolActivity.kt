package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.semicolon.domain.enum.DateType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHubSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchUserRvAdapter
import com.semicolon.walkhub.ui.hub.adapter.HubViewPagerAdapter
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.ui.hub.model.toRvData
import com.semicolon.walkhub.viewmodel.hub.HubUserRankViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubSchoolActivity @Inject constructor(
) : BaseActivity<ActivityHubSchoolBinding>(
    R.layout.activity_hub_school
) {

    private val vm: HubUserRankViewModel by viewModels()
    private var schoolName = "no data"

    private lateinit var mAdapter: HubSearchUserRvAdapter
    private var rvHubUserData = arrayListOf<UserRankRvData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val schoolType = intent.getBooleanExtra("type", true)

        if (schoolType) {
            vm.fetchMySchoolUserRank(DateType.WEEK)
        } else {
            vm.fetchSchoolUserRank(DateType.WEEK)
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: HubUserRankViewModel.Event) = when (event) {

        is HubUserRankViewModel.Event.FetchMySchoolUserRank -> {
            setUserRank(event.ourSchoolUserRankData.rankingList.map { it.toRvData() })
        }
        is HubUserRankViewModel.Event.FetchUserRank -> {
            setUserRank(event.userRankData.rankList.map { it.toRvData() })
        }
    }

    private fun setUserRank(data: List<UserRankRvData>) {

        for (i: Int in 0..data.size - 1) {
            rvHubUserData.add(data[i])
        }

        binding.rvSchool.adapter?.notifyDataSetChanged()
    }

    override fun initView() {

        setToolbar()
        setTab()
        setAdapter()
    }

    private fun setToolbar() {

        schoolName = intent.getStringExtra("name")!!

        binding.toolbarTitle.text = schoolName

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setTab() {

        binding.vpHub.adapter = HubViewPagerAdapter(this)

        val tabTitles = listOf("랭킹", "정보")

        TabLayoutMediator(binding.tbHub, binding.vpHub) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun setAdapter() {

        mAdapter = HubSearchUserRvAdapter(rvHubUserData)

        binding.rvSchool.layoutManager = LinearLayoutManager(this)
        binding.rvSchool.setHasFixedSize(true)
        binding.rvSchool.adapter = mAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_hub_search, menu)

        val mSearch = menu!!.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView

        mSearchView.queryHint = "학교를 입력하세요"

        mSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                binding.searchBlack.visibility = View.VISIBLE
                binding.rvSchool.visibility = View.VISIBLE
                binding.vpHub.visibility = View.GONE
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                binding.searchBlack.visibility = View.GONE
                binding.rvSchool.visibility = View.GONE
                binding.vpHub.visibility = View.VISIBLE
                return true
            }
        })

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = menuView(false)

            override fun onQueryTextChange(newText: String): Boolean {
                mAdapter.filter.filter(newText)

                return menuView(true)
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    private fun menuView(state: Boolean): Boolean {

        binding.apply {
            if (state) {
                searchBlack.visibility = View.VISIBLE
                rvSchool.visibility = View.VISIBLE
            } else {
                searchBlack.visibility = View.GONE
                rvSchool.visibility = View.GONE
            }

            return false
        }
    }

}