package com.semicolon.walkhub.ui.hub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.semicolon.walkhub.R
import com.semicolon.walkhub.databinding.HubSchoolRankViewBinding
import com.semicolon.walkhub.ui.hub.model.HubSchoolRank.OtherSchool
import com.semicolon.walkhub.ui.hub.ui.HubSchoolActivity
import com.semicolon.walkhub.util.loadFromUrl
import com.semicolon.walkhub.util.visible

class HubSchoolRankRvAdapter(
    val data: List<OtherSchool>
) : RecyclerView.Adapter<HubSchoolRankRvAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HubSchoolRankViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: OtherSchool) {

            itemView.setOnClickListener {
                val intent = Intent(context, HubSchoolActivity::class.java)
                intent.putExtra("type", false)
                intent.putExtra("name", item.name)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
            }

            binding.ivSchool.loadFromUrl(item.logo_image_url)
            if (item.name.length > 10) {
                val schoolName = item.name.substring(0, 11) + "..."
                binding.tvName.text = schoolName
            } else {
                binding.tvName.text = item.name
            }
            binding.tvWalkCount.text = "총 ${item.walk_count} 걸음 / 총 ${item.student_count} 명"
            binding.tvRate.text = item.ranking.toString()

            when (item.ranking) {
                1 -> {
                    binding.ivRate.visible()
                    binding.ivRate.setImageResource(R.drawable.ic_rank_first)
                }
                2 -> {
                    binding.ivRate.visible()
                    binding.ivRate.setImageResource(R.drawable.ic_rank_second)
                }
                3 -> {
                    binding.ivRate.visible()
                    binding.ivRate.setImageResource(R.drawable.ic_rank_third)
                }
            }

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