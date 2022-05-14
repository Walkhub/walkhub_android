package com.semicolon.walkhub.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.semicolon.domain.entity.level.LevelEntity
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.FragmentHomeBinding
import com.semicolon.walkhub.extensions.repeatOnStarted
import com.semicolon.walkhub.ui.analysis.ActivityAnalysisActivity
import com.semicolon.walkhub.ui.base.BaseFragment
import com.semicolon.walkhub.ui.home.model.HomeData
import com.semicolon.walkhub.ui.home.model.RankData
import com.semicolon.walkhub.ui.measure.MeasureHomeActivity
import com.semicolon.walkhub.ui.measure.MeasuringActivity
import com.semicolon.walkhub.ui.notification.ui.NotificationActivity
import com.semicolon.walkhub.util.isVisible
import com.semicolon.walkhub.util.loadCircleFromUrl
import com.semicolon.walkhub.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {

    private val vm: HomeViewModel by viewModels()
    private val levelList = mutableListOf<LevelEntity>()
    private val foodImage = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        vm.fetchHomeValue()
        vm.fetchLevelList()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: HomeViewModel.Event) = when (event) {
        is HomeViewModel.Event.LevelList -> {
            levelList.clear()
            levelList.addAll(event.levelList)
            Unit
        }

        is HomeViewModel.Event.FetchHomeValue -> {
            val image = if (levelList.isNotEmpty())
                levelList.last { it.calories <= event.homeData.burnedKilocalories }.foodImageUrl else ""
            if (image != foodImage)
                Glide.with(this)
                    .load(image)
                    .into(binding.iv)
            setHomeValue(event.homeData)
        }

        is HomeViewModel.Event.FetchSchoolRank -> {

        }

        is HomeViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
        is HomeViewModel.Event.StartMeasureHome -> {
            Intent(context, MeasureHomeActivity::class.java).run {
                startActivity(this)
            }
        }
        is HomeViewModel.Event.StartMeasure -> {
            Intent(context, MeasuringActivity::class.java).run {
                startActivity(this)
            }
        }
    }

    override fun initView() {
        binding.firstCardView.setOnClickListener {
            val intent = Intent(context, ActivityAnalysisActivity::class.java)
            startActivity(intent)
        }

        binding.homeStartMeasureCl.setOnClickListener {
            vm.checkMeasuringState()
        }

        binding.btNotification.setOnClickListener {
            val intent = Intent(context, NotificationActivity::class.java)
            startActivity(intent)
        }


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