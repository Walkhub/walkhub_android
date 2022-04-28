package com.semicolon.walkhub.ui.hub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.ItemHubInfoNoticeBinding
import com.semicolon.walkhub.ui.hub.model.HubInfoNoticeRvData
import kotlin.collections.ArrayList

class HubInfoNoticeRvAdapter(
    private val dataList: ArrayList<HubInfoNoticeRvData>
) : RecyclerView.Adapter<HubInfoNoticeRvAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ItemHubInfoNoticeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HubInfoNoticeRvData) {

            binding.tvTitle.text = item.title
            binding.tvDate.text = item.date

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHubInfoNoticeBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}