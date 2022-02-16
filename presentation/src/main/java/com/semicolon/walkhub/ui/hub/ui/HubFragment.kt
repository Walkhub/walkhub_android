package com.semicolon.walkhub.ui.hub.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHubBinding
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.hub.adapter.HubSchoolRankRvAdapter
import com.semicolon.walkhub.ui.hub.model.MySchool
import com.semicolon.walkhub.ui.hub.model.School
import com.semicolon.walkhub.util.loadCircleFromUrl

class HubFragment : BaseFragment<FragmentHubBinding>(
    R.layout.fragment_hub
) {

    private var schoolRvData = arrayListOf<School>()
    private lateinit var mAdapter: HubSchoolRankRvAdapter

    private var fakeImage = "http://goo.gl/gEgYUd"

    override fun initView() {

        setAdapter()
        setMySchool(MySchool(3, "대덕소프트웨어마이스터고등학교", fakeImage, 103001, 3, 3))
        setFakeData()
    }

    private fun setAdapter() {

        mAdapter = HubSchoolRankRvAdapter(schoolRvData)

        binding.rvHubRank.layoutManager = LinearLayoutManager(context)
        binding.rvHubRank.setHasFixedSize(true)
        binding.rvHubRank.adapter = mAdapter
    }

    private fun setMySchool(school: MySchool) {

        binding.ivMySchool.loadCircleFromUrl(school.logo_image_url)
        binding.tvMySchoolName.text = school.name
        binding.tvMySchoolInfo.text = "${school.grade} 학년 ${school.class_num} 반"
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setFakeData() {

        for (i: Int in 1..10) {
            schoolRvData.add(School(1, "광주소프트웨아마이스터고등학교", 1,100, fakeImage, 1315))
            schoolRvData.add(School(2, "대구소프트웨어마이스터고등학교", 10, 200, fakeImage, 15123))
        }

        binding.rvHubRank.adapter?.notifyDataSetChanged()
    }

    override fun handleEvent() {
    }
}