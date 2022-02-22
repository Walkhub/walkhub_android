package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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
import com.semicolon.walkhub.ui.hub.model.MySchoolUserRankData
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.ui.hub.model.toRvData
import com.semicolon.walkhub.util.invisible
import com.semicolon.walkhub.util.visible
import com.semicolon.walkhub.viewmodel.hub.HubSearchUserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HubSchoolActivity @Inject constructor(
) : BaseActivity<ActivityHubSchoolBinding>(
    R.layout.activity_hub_school
) {

    private val vm: HubSearchUserViewModel by viewModels()
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

    override fun initView() {

        setToolbar()
        setTab()
        setAdapter()
    }

    private fun handleEvent(event: HubSearchUserViewModel.Event) = when (event) {

        is HubSearchUserViewModel.Event.FetchMySchoolUserRank -> {
            setUserRank(event.mySchoolUserRankData.rankingList.map { it.toRvData() })
        }
        is HubSearchUserViewModel.Event.FetchUserRank -> {
            setUserRank(event.userRankData.rankList.map { it.toRvData() })
        }
    }


    private fun setUserRank(data: List<UserRankRvData>) {

        for (i: Int in 0..data.size - 1) {
            rvHubUserData.add(data[i])
        }

        binding.rvSchool.adapter?.notifyDataSetChanged()
    }

    private fun setToolbar() {

        schoolName = intent.getStringExtra("name")!!

        binding.toolbarTitle.text = schoolName

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_hub_search, menu)

        val mSearch = menu.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView

        mSearchView.queryHint = "학교를 입력하세요"

        mSearch.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
                binding.searchBlack.visible()
                binding.rvSchool.visible()
                binding.vpHub.invisible()
                return true
            }

            override fun onMenuItemActionCollapse(p0: MenuItem?): Boolean {
                binding.vpHub.visible()
                binding.searchBlack.invisible()
                binding.rvSchool.invisible()
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
                searchBlack.visible()
                rvSchool.visible()
            } else {
                searchBlack.invisible()
                rvSchool.invisible()
            }

            return false
        }
    }

}