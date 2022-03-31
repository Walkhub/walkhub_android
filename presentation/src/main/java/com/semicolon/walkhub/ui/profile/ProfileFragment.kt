package com.semicolon.walkhub.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentProfileBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.profile.model.MyPageData
import com.semicolon.walkhub.ui.profile.setting.ui.SettingActivity
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.loadFromUrl
import com.semicolon.walkhub.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    R.layout.fragment_profile
) {
    private val vm: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchMyPage()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: ProfileViewModel.Event) = when (event) {
        is ProfileViewModel.Event.FetchMyPage -> {
            setProfileValue(event.myPageData)
        }

        is ProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setProfileValue(profileData: MyPageData) {
        binding.name.text = profileData.name
        binding.walkCount.text = profileData.dailyWalkCountGoal.toString()
        binding.gradeClass.text = profileData.grade.toString()
        binding.schoolName.text = profileData.schoolName
        binding.badgeName.text = profileData.titleBadge.badgeName
        binding.ratingName.text = profileData.level.levelName
        profileData.schoolImageUrl.let { binding.schoolLogo.loadCircleFromUrl(it) }
        profileData.profileImageUrl.let { binding.myPicture.loadCircleFromUrl(it) }
        profileData.titleBadge.badgeImageUrl.let { binding.badgeImage.loadFromUrl(it) }
        profileData.level.levelImageUrl.let { binding.rating.loadFromUrl(it) }
    }


}