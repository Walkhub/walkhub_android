package com.semicolon.walkhub.ui.hub.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHubSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchUserRvAdapter
import com.semicolon.walkhub.ui.hub.adapter.HubViewPagerAdapter
import com.semicolon.walkhub.ui.hub.model.SearchUserData
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
    private var rvHubUserData = arrayListOf<SearchUserData.UserInfo>()

    private var schoolId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        schoolId = intent.getIntExtra("schoolId", 0)

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
        is HubSearchUserViewModel.Event.SearchUser -> {
            event.userData.userList.let {
                setUserRank(it)
            }
        }
        is HubSearchUserViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setUserRank(data: List<SearchUserData.UserInfo>) {
        rvHubUserData.clear()

        for (element in data) {
            rvHubUserData.add(element)
        }

        binding.rvSchool.adapter?.notifyDataSetChanged()
    }

    private fun setToolbar() {
        schoolName = intent.getStringExtra("name").toString()

        binding.toolbarTitle.text = schoolName

        setSupportActionBar(binding.toolbar)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
        }
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

        binding.rvSchool.apply {
            layoutManager = LinearLayoutManager(this@HubSchoolActivity)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_hub_search, menu)

        val mSearch = menu.findItem(R.id.action_search)
        val mSearchView = mSearch.actionView as SearchView

        mSearchView.queryHint = "이름으로 검색하세요"

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

        mSearchView.background = null

        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = menuView(false)

            override fun onQueryTextChange(newText: String): Boolean {

                if (newText.isNotEmpty()) {
                    vm.searchUserDebounce(schoolId, newText, HubRankFragment.dateType)
                }

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