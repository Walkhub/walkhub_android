package com.semicolon.walkhub.ui.hub.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.semicolon.walkhub.ui.hub.ui.HubInfoFragment
import com.semicolon.walkhub.ui.hub.ui.HubRankFragment

class HubViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(HubRankFragment(), HubInfoFragment())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment =
        fragmentList[position]
}