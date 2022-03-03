package com.semicolon.walkhub.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHomeBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.analysis.ActivityAnalysisActivity
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.home.model.HomeData
import com.semicolon.walkhub.ui.home.model.RankData
import com.semicolon.walkhub.ui.measure.MeasureHomeActivity
import com.semicolon.walkhub.util.isVisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {

    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchHomeValue()
        vm.fetchSchoolRank()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: HomeViewModel.Event) = when (event) {
        is HomeViewModel.Event.FetchHomeValue -> {
            setHomeValue(event.homeData)
        }

        is HomeViewModel.Event.FetchSchoolRank -> {
            setSchoolRank(event.rankData)
        }

        is HomeViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        binding.firstCardView.setOnClickListener {
            val intent = Intent(context, ActivityAnalysisActivity::class.java)
            startActivity(intent)
        }
        binding.secondCardView.setOnClickListener {
            val intent = Intent(context, MeasureHomeActivity::class.java)
            startActivity(intent)
        }
        Glide.with(this)
            .load("https://s3.us-west-2.amazonaws.com/secure.notion-static.com/43380b84-6dc9-44fc-8b51-6aef4d9f1faf/커피icon.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Credential=AKIAT73L2G45EIPT3X45%2F20220303%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20220303T005859Z&X-Amz-Expires=86400&X-Amz-Signature=50863010f7f84127f4b0dfdbaa0d59b8da48e5c0b994ad2bfed0a0c3d2594ddd&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22%25EC%25BB%25A4%25ED%2594%25BCicon.png%22&x-id=GetObject")
            .into(binding.iv)
    }

    private fun setHomeValue(homeData: HomeData) {
        val calories = String.format("%.1f", homeData.burnedKilocalories)
        binding.tvWalk.text = homeData.stepCount.toString()
        binding.tvCalories.text = calories
        binding.tvDistance.text = homeData.traveledDistanceAsMeter.toString()
        binding.cpb.progress = homeData.stepCount.toFloat()
        binding.cpb.progressMax = 7000f
        binding.tvTime.text = (homeData.exerciseTimeAsMilli / 60000).toString()
    }

    private fun setSchoolRank(rankData: List<RankData>) {
        binding.third.isVisible()
        binding.tvName.text = rankData[0].name
        binding.tvName2.text = rankData[1].name
        binding.tvName3.text = rankData[2].name
        binding.tvWalkCount.text = rankData[0].walkCount.toString()
        binding.tvWalkCount2.text = rankData[1].walkCount.toString()
        binding.tvWalkCount3.text = rankData[2].walkCount.toString()
        rankData[0].logoImageUrl?.let { binding.ivProfile.loadCircleFromUrl(it) }
        rankData[1].logoImageUrl?.let { binding.ivProfile2.loadCircleFromUrl(it) }
        rankData[2].logoImageUrl?.let { binding.ivProfile3.loadCircleFromUrl(it) }
    }
}