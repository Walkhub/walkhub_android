package com.semicolon.walkhub.ui.hub.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.domain.enum.MoreDateType
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityHubSearchSchoolBinding
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchSchoolRvAdapter
import com.semicolon.walkhub.ui.hub.model.HubSchoolRankData
import com.semicolon.walkhub.util.onTextChanged
import com.semicolon.walkhub.viewmodel.hub.HubSearchSchoolViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HubSearchSchoolActivity : BaseActivity<ActivityHubSearchSchoolBinding> (
    R.layout.activity_hub_search_school
) {

    private val vm: HubSearchSchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initView() {}
}