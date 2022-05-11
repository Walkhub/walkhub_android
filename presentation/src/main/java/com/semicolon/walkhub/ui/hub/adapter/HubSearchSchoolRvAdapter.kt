package com.semicolon.walkhub.ui.hub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.databinding.HubSchoolRankViewBinding
import com.semicolon.walkhub.ui.hub.model.SearchSchoolData
import com.semicolon.walkhub.ui.hub.ui.HubSchoolActivity
import com.semicolon.walkhub.util.loadFromUrl

class HubSearchSchoolRvAdapter(
    val data: List<SearchSchoolData.SchoolInfo>
) : RecyclerView.Adapter<HubSearchSchoolRvAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HubSchoolRankViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SearchSchoolData.SchoolInfo) {

            itemView.setOnClickListener {
                val intent = Intent(context, HubSchoolActivity::class.java)
                intent.putExtra("type", false)
                intent.putExtra("name", item.schoolName)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

            item.logoImageUrl?.let { binding.ivSchool.loadFromUrl(it) }

            if (item.schoolName.length > 10) {
                val schoolName = item.schoolName.substring(0, 11) + "..."
                binding.tvName.text = schoolName
            } else {
                binding.tvName.text = item.schoolName
            }

            val tvWalkCountText = "총 ${item.walkCount} 걸음 / 총 ${item.walkCount} 명"
            binding.tvWalkCount.text = tvWalkCountText
            binding.tvRate.text = item.ranking.toString()

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HubSchoolRankViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}