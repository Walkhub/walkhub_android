package com.semicolon.walkhub.ui.register

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivitySearchSchoolBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.register.adapter.SearchSchoolAdapter
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.viewmodel.SearchSchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchSchoolActivity : BaseActivity<ActivitySearchSchoolBinding>(
    R.layout.activity_search_school
) {
    private val vm: SearchSchoolViewModel by viewModels()

    private val schoolRvData = arrayListOf<SecondSearchSchoolData.SchoolInfo>()

    private lateinit var mAdapter: SearchSchoolAdapter

    private fun handleEvent(event: SearchSchoolViewModel.Event) = when (event) {
        is SearchSchoolViewModel.Event.SearchSchoolTwo -> {
            setSchoolData(event.secondSearchSchoolData)
        }
        is SearchSchoolViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    override fun initView() {
        setAdapter()

        binding.btBack.setOnClickListener {
            finish()
        }

        binding.etSchool.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                vm.searchSchool(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setSchoolData(list: SecondSearchSchoolData) {
        schoolRvData.clear()

        for(i: Int in list.schoolList.indices) {
            schoolRvData.add(list.schoolList[i])
        }

        binding.rvSchoolRank.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        mAdapter = SearchSchoolAdapter(schoolRvData)

        binding.rvSchoolRank.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }
}