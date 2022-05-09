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
import android.util.Log
import android.view.View
import com.semicolon.walkhub.databinding.ActivityNotificationBinding
import com.semicolon.walkhub.ui.HomeActivity
import com.semicolon.walkhub.ui.MainActivity
import com.semicolon.walkhub.ui.analysis.ActivityAnalysisActivity
import com.semicolon.walkhub.ui.challenge.ChallengeFragment
import com.semicolon.walkhub.ui.hub.ui.HubRankFragment
import gun0912.tedimagepicker.util.ToastUtil.context


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
                    val intent =
                        Intent(holder.itemView.context, ActivityAnalysisActivity::class.java)
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
        val binding: NotificationItemBinding,
        private val binding2: ActivityNotificationBinding
    ) :
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

            // showDate(item)

            binding.executePendingBindings()

            if (item.notificationId < 1) {
                binding2.nullNotification.visibility = View.VISIBLE
            }
        }

        enum class TimeValue(val value: Int,val maximum : Int, val msg : String) {
            SEC(60,60,"분 전"),
            MIN(60,24,"시간 전"),
            HOUR(24,30,"일 전"),
            DAY(30,12,"달 전"),
            MONTH(12,Int.MAX_VALUE,"년 전")
        }

//        fun timeDiff(time : Long):String{
//            val curTime = System.currentTimeMillis()
//            var diffTime = (curTime- timeStamp) / 1000
//            var msg: String? = null
//            if(diffTime < TimeValue.SEC.value )
//                msg= "방금 전"
//            else {
//                for (i in TimeValue.values()) {
//                    diffTime /= i.value
//                    if (diffTime < i.maximum) {
//                        msg=i.msg
//                        break
//                    }
//                }
//            }
//        }


        @SuppressLint("SimpleDateFormat")
        var curTime = System.currentTimeMillis()
        val dateFormat = SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
        val curDate = dateFormat.format(curTime)

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NotificationItemBinding.inflate(layoutInflater, parent, false)
                val binding2 = ActivityNotificationBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, binding2)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

