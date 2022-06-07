package com.semicolon.walkhub.ui.profile.setting.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.ThirdschoolitemBinding
import com.semicolon.walkhub.ui.profile.setting.ui.ModifyProfileActivity
import com.semicolon.walkhub.ui.profile.setting.ui.model.ThirdSearchSchoolData
import com.semicolon.walkhub.util.loadFromUrl


class ThirdSchoolAdapter(
    private val dataList: ArrayList<ThirdSearchSchoolData.SchoolInfo>,
) : RecyclerView.Adapter<ThirdSchoolAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        val id = dataList[position].schoolId
        val school = dataList[position].schoolName
        holder.itemView.tag = position

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ModifyProfileActivity::class.java)
            intent.putExtra("data", id)
            intent.putExtra("school", school)
            intent.putExtra("next", true)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ThirdschoolitemBinding) :
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
                val binding = ThirdschoolitemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}