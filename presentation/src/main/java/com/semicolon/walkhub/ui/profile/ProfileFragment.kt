package com.semicolon.walkhub.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.gun0912.tedpermission.provider.TedPermissionProvider
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentProfileBinding
import com.semicolon.walkhub.extensions.UrlConverter
import com.semicolon.walkhub.extensions.fetchImage
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.home.model.HomeData
import com.semicolon.walkhub.ui.profile.model.MyPageData
import com.semicolon.walkhub.ui.profile.setting.ui.SettingActivity
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.util.loadFromUrl
import com.semicolon.walkhub.viewmodel.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(
    R.layout.fragment_profile
) {
    private val vm: ProfileViewModel by viewModels()

    private lateinit var profileImage: String
    private var schoolId by Delegates.notNull<Long>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchMyPage()
        //vm.fetchHomeValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: ProfileViewModel.Event) = when (event) {
        is ProfileViewModel.Event.FetchMyPage -> {
            setProfileValue(event.myPageData)
            schoolId = event.myPageData.schoolId
        }

        is ProfileViewModel.Event.FetchHome -> {
            setHomeValue(event.homeData)

        }

        is ProfileViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.setting.setOnClickListener {
            val intent = Intent(activity, SettingActivity::class.java)
            intent.putExtra("profile_image", profileImage)
            intent.putExtra("school_id", schoolId)
            startActivity(intent)

        }
    }

    private fun setHomeValue(homeData: HomeData) {
        val calories = String.format("%.1f", homeData.burnedKilocalories)
        binding.walkCount.text = homeData.stepCount.toString()
        binding.calorie.text = calories
        binding.distance.text = homeData.traveledDistanceAsMeter.toString()
        binding.walkTime.text = (homeData.exerciseTimeAsMilli / 60000).toString()
    }

    private fun setProfileValue(profileData: MyPageData) {
        binding.name.text = profileData.name
        binding.grade.text = profileData.grade.toString()
        binding.classes.text = profileData.classNum.toString()
        binding.schoolName.text = profileData.schoolName
        binding.badgeName.text = profileData.titleBadge.badgeName
        binding.ratingName.text = profileData.level.levelName
        profileData.schoolImageUrl.let { binding.schoolLogo.loadCircleFromUrl(it) }
        profileData.profileImageUrl.let {
            if (it != null) {
                profileImage = profileData.profileImageUrl.toString()
                binding.myPicture.loadCircleFromUrl(it)
            }
        }
        profileData.titleBadge.badgeImageUrl.let { binding.badgeImage.loadFromUrl(it) }
        profileData.level.levelImageUrl.let { binding.rating.loadFromUrl(it) }
    }

}