package com.semicolon.walkhub.ui.notification.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.domain.enums.NotificationReturnType
import com.semicolon.domain.enums.NotificationType
import com.semicolon.walkhub.databinding.NotificationItemBinding
import com.semicolon.walkhub.databinding.SchoolitemBinding
import com.semicolon.walkhub.ui.notification.model.NotificationData
import com.semicolon.walkhub.ui.register.model.SecondSearchSchoolData
import com.semicolon.walkhub.ui.register.ui.Register
import com.semicolon.walkhub.util.loadFromUrl
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import android.content.Intent
import com.semicolon.walkhub.ui.analysis.ActivityAnalysisActivity
import com.semicolon.walkhub.ui.challenge.ChallengeFragment
import com.semicolon.walkhub.ui.hub.ui.HubRankFragment
import gun0912.tedimagepicker.util.ToastUtil.context


class NotificationAdapter (
    private val dataList: ArrayList<NotificationData.NotificationValue>
    ) : RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            val item = dataList[position]
            val notificationItem = dataList[position].data

            holder.itemView.setOnClickListener {
                when(notificationItem) {
                    NotificationReturnType.NOTICE -> {
                        val intent = Intent(context, HubRankFragment::class.java)
                        intent.run { context.startActivity(this) }
                    }

                    NotificationReturnType.CHALLENGE -> {
                        val intent = Intent(context, ChallengeFragment::class.java)
                        intent.run { context.startActivity(this) }
                    }

                    NotificationReturnType.EXERCISE -> {
                        val intent = Intent(context, ActivityAnalysisActivity::class.java)
                        intent.run { context.startActivity(this) }
                    }
                }
            }

            holder.bind(item)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder.from(parent)
        }

        class ViewHolder private constructor(val binding: NotificationItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: NotificationData.NotificationValue) {

                binding.ivLogo.loadFromUrl(item.writer.writerImage)

                if (item.content.length > 20) {
                    val title = item.content.substring(0, 20) + "..."
                    binding.tvContent.text = title
                } else {
                    binding.tvContent.text = item.content
                }

                binding.tvTitle.text = item.title

                showDate(item)
                binding.executePendingBindings()
            }

            private object TIME_MAXIMUM {
                const val SEC = 60
                const val MIN = 60
                const val HOUR = 24
                const val DAY = 30
                const val MONTH = 12
            }

            @SuppressLint("SimpleDateFormat")
            private fun showDate(item: NotificationData.NotificationValue) {
                val stringDate: String = item.createAt
                val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date: Date = format.parse(stringDate)
                calculateTime(date)
            }

            private fun calculateTime(date: Date): String {
                val curTime = System.currentTimeMillis()
                val regTime: Long = date.getTime()
                var diffTime = (curTime - regTime) / 1000
                var msg: String? = null
                when {
                    diffTime < TIME_MAXIMUM.SEC -> {
                        msg = diffTime.toString() + "초전"
                    }

                    TIME_MAXIMUM.SEC.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MIN -> {
                        msg = diffTime.toString() + "분전"
                    }

                    TIME_MAXIMUM.MIN.let { diffTime /= it; diffTime } < TIME_MAXIMUM.HOUR -> {
                        msg = diffTime.toString() + "시간전"
                    }

                    TIME_MAXIMUM.HOUR.let { diffTime /= it; diffTime } < TIME_MAXIMUM.DAY -> {
                        msg = diffTime.toString() + "일전"
                    }

                    TIME_MAXIMUM.DAY.let { diffTime /= it; diffTime } < TIME_MAXIMUM.MONTH -> {
                        msg = diffTime.toString() + "달전"
                    }

                    else -> {
                        msg = diffTime.toString() + "년전"
                    }
                }
                binding.tvTimeLater.text = msg

                return msg
            }

            companion object {
                fun from(parent: ViewGroup): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = NotificationItemBinding.inflate(layoutInflater, parent, false)

                    return ViewHolder(binding)
                }
            }
        }

        override fun getItemCount(): Int {
            return dataList.size
        }
}

