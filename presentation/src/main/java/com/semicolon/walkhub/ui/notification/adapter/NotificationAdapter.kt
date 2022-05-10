package com.semicolon.walkhub.ui.notification.adapter

import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.domain.enums.NotificationReturnType
import com.semicolon.walkhub.databinding.NotificationItemBinding
import com.semicolon.walkhub.ui.notification.model.NotificationData
import com.semicolon.walkhub.util.loadFromUrl
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import android.view.LayoutInflater
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.analysis.ActivityAnalysisActivity

class NotificationAdapter(
    private val dataList: ArrayList<NotificationData.NotificationValue>
) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]
        val notificationItem = dataList[position].type

        holder.itemView.setOnClickListener {
            when (notificationItem) {
                NotificationReturnType.NOTICE -> {
                    val intent = Intent(holder.itemView.context, MainActivity::class.java)
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }

                NotificationReturnType.CHALLENGE -> {
                    val intent = Intent(holder.itemView.context, MainActivity::class.java)
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }

                NotificationReturnType.EXERCISE -> {
                    val intent = Intent(holder.itemView.context, ActivityAnalysisActivity::class.java)
                    ContextCompat.startActivity(holder.itemView.context, intent, null)
                }
            }
        }

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(
        val binding: NotificationItemBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NotificationData.NotificationValue) {

            item.writer.writerImage?.let { binding.ivLogo.loadFromUrl(it) }

            if (item.content.length > 20) {
                val title = item.content.substring(0, 20) + "..."
                binding.tvContent.text = title
            } else {
                binding.tvContent.text = item.content
            }

            binding.tvTitle.text = item.title

            binding.executePendingBindings()

            timeAdapter(this, item.createAt)
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NotificationItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }

            private fun timeAdapter(viewHolder: ViewHolder, time: String) {
                val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.KOREA)
                val subTime = dateFormat.parse(time)
                val date = Date(System.currentTimeMillis())
                val currentTime = dateFormat.format(date)
                val getTime = dateFormat.format(subTime)
                val longCurrentTime = dateFormat.parse(currentTime).time
                val longGetTime = dateFormat.parse(getTime).time
                val diff = (longCurrentTime - longGetTime) / 1000
                val dayDiff = (diff / 86400)
                if (dayDiff < 0 || dayDiff >= 31) {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA)
                    viewHolder.binding.tvTimeLater.text = dateFormat.format(subTime)
                } else {
                    if (dayDiff <= 0) {
                        when (diff) {
                            in 0..60 ->
                                viewHolder.binding.tvTimeLater.text = "방금"
                            in 61..120 -> viewHolder.binding.tvTimeLater.text = "1분전"
                            in 121..3600 ->
                                viewHolder.binding.tvTimeLater.text = "${diff / 60}분 전"
                            in 3601..7200 -> viewHolder.binding.tvTimeLater.text = "1시간 전"
                            else -> viewHolder.binding.tvTimeLater.text = "${diff / 3600}시간 전"
                        }
                    } else {
                        when (dayDiff) {
                            1.toLong() -> viewHolder.binding.tvTimeLater.text = "어제"
                            in 2..6 -> viewHolder.binding.tvTimeLater.text = "${dayDiff}일 전"
                            else -> viewHolder.binding.tvTimeLater.text = "${dayDiff / 7}주 전"
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

