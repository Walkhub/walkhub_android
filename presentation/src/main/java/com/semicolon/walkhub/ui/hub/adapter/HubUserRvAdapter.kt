package com.semicolon.walkhub.ui.hub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.HubUserSearchViewBinding
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.util.loadFromUrl
import kotlin.collections.ArrayList

class HubUserRvAdapter(
    val dataList: ArrayList<UserRankRvData>
) : RecyclerView.Adapter<HubUserRvAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HubUserSearchViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserRankRvData) {

            binding.ivUserProfile.loadFromUrl(item.profileUrl)
            binding.tvName.text = item.name

            val tvWalkCountText = "${item.walkCount} 걸음"
            binding.tvWalkCount.text = tvWalkCountText

            val tvRateText = "${item.rank} 등"
            binding.tvRate.text = tvRateText

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HubUserSearchViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}