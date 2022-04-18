package com.semicolon.walkhub.ui.register.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.SchoolitemBinding
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.util.loadFromUrl

class SearchSchoolAdapter (
    private val dataList: ArrayList<SecondSearchSchoolData.SchoolInfo>
) : RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: SchoolitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SecondSearchSchoolData.SchoolInfo) {

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
                val binding = SchoolitemBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}