package com.semicolon.walkhub.ui.notification.ui

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.ActivityNotificationBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseActivity
import com.semicolon.walkhub.ui.hub.adapter.HubSearchSchoolRvAdapter
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.ui.notification.adapter.NotificationAdapter
import com.semicolon.walkhub.ui.notification.model.NotificationData
import com.semicolon.walkhub.viewmodel.hub.HubUserViewModel
import com.semicolon.walkhub.viewmodel.notification.NotificationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationActivity : BaseActivity<ActivityNotificationBinding>(
    R.layout.activity_notification
) {

    private val vm: NotificationViewModel by viewModels()

    private val notificationData = arrayListOf<NotificationData.NotificationValue>()
    private lateinit var rAdapter: NotificationAdapter

    override fun initView() {
        setAdapter()

        vm.fetchNotification()

        binding.ibBack.setOnClickListener {
            finish()
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: NotificationViewModel.Event) = when (event) {
        is NotificationViewModel.Event.NotificationValue -> {
            Log.d(TAG, "handleEvent: ${event.notificationData}")

            when {
                event.notificationData.notificationValue?.isEmpty() == true -> {
                    binding.nullNotification.visibility = View.VISIBLE
                }

                event.notificationData.notificationValue?.isNotEmpty() == true -> {
                    setNotificationData(event.notificationData)
                    binding.nullNotification.visibility = View.GONE
                }

                else -> {}
            }
        }

        is NotificationViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setNotificationData(list: NotificationData) {
        notificationData.clear()

        for (i: Int in list.notificationValue?.indices!!) {
            notificationData.add(list.notificationValue[i])
        }

        binding.rvNotification.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = NotificationAdapter(notificationData)

        binding.rvNotification.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }
}