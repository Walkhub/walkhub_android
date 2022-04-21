package com.semicolon.walkhub.ui.profile.setting.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySearchSchoolBinding
import com.semicolon.walkhub.databinding.ActivitySettingSearchSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.profile.setting.ui.adapter.ThirdSchoolAdapter
import com.semicolon.walkhub.ui.profile.setting.ui.model.ThirdSearchSchoolData
import com.semicolon.walkhub.viewmodel.profile.setting.SettingSearchSchoolViewModel

class SettingSearchSchoolActivity : BaseActivity<ActivitySettingSearchSchoolBinding>(
    R.layout.activity_setting_search_school
) {
    private val vm: SettingSearchSchoolViewModel by viewModels()

    private val schoolRvData = arrayListOf<ThirdSearchSchoolData.SchoolInfo>()

    private lateinit var mAdapter: ThirdSchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: SettingSearchSchoolViewModel.Event) = when (event) {
        is SettingSearchSchoolViewModel.Event.SearchSchoolThird -> {
            setSchoolData(event.thirdSearchData)
        }
        is SettingSearchSchoolViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.etSchool.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.searchSchool(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })

        setAdapter()

        binding.btBack.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSchoolData(list: ThirdSearchSchoolData) {
        schoolRvData.clear()

        for(i: Int in list.schoolList.indices) {
            schoolRvData.add(list.schoolList[i])
        }

        binding.rvSchoolRank.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        mAdapter = ThirdSchoolAdapter(schoolRvData)

        binding.rvSchoolRank.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
}