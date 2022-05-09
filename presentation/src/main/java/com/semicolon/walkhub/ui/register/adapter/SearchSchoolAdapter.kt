package com.semicolon.walkhub.ui.register.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.SchoolitemBinding
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.util.loadFromUrl
import android.content.Intent
import androidx.core.content.ContextCompat
import com.semicolon.walkhub.ui.register.ui.Register
import com.semicolon.walkhub.util.loadCircleFromUrl
import gun0912.tedimagepicker.util.ToastUtil.context


class SearchSchoolAdapter(
    private val dataList: ArrayList<SecondSearchSchoolData.SchoolInfo>
) : RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        val id = dataList[position].schoolId
        val school = dataList[position].schoolName
        holder.itemView.tag = position

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Register::class.java)
            intent.putExtra("data", id)
            intent.putExtra("school", school)
            intent.putExtra("movePage", true)
            ContextCompat.startActivity(holder.itemView.context, intent, null)
        }

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder(val binding: SchoolitemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SecondSearchSchoolData.SchoolInfo) {

            item.logoImageUrl?.let { binding.ivSchool.loadFromUrl(it) }

            if (item.schoolName.length > 16) {
                val schoolName = item.schoolName.substring(0, 16) + "..."
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