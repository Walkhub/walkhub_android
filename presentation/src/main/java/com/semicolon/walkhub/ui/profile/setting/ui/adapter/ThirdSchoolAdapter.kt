package com.semicolon.walkhub.ui.profile.setting.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.ThirdshcoolitemBinding
import com.semicolon.walkhub.ui.profile.setting.ui.model.ThirdSearchSchoolData
import com.semicolon.walkhub.util.loadFromUrl

class ThirdSchoolAdapter (
    private val dataList: ArrayList<ThirdSearchSchoolData.SchoolInfo>
) : RecyclerView.Adapter<ThirdSchoolAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ThirdshcoolitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ThirdSearchSchoolData.SchoolInfo) {

            binding.ivSchool.loadFromUrl(item.logoImageUrl)

            if (item.schoolName.length > 10) {
                val schoolName = item.schoolName.substring(0, 11) + "..."
                binding.tvSchool.text = schoolName
            } else {
                binding.tvSchool.text = item.schoolName
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ThirdshcoolitemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}