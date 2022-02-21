package com.semicolon.walkhub.ui.hub.adapter

import android.annotation.SuppressLint
import android.content.ContentValues
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.databinding.HubUserSearchViewBinding
import com.semicolon.walkhub.ui.hub.model.UserRankRvData
import com.semicolon.walkhub.util.loadFromUrl
import java.util.*
import kotlin.collections.ArrayList

class HubSearchUserRvAdapter(
    val dataList: ArrayList<UserRankRvData>
) : RecyclerView.Adapter<HubSearchUserRvAdapter.ViewHolder>(), Filterable {

    private var dataFilterList: ArrayList<UserRankRvData> = dataList

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataFilterList.get(position)

        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: HubUserSearchViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserRankRvData) {

            binding.ivUserProfile.loadFromUrl(item.profileUrl)
            binding.tvName.text = item.name
            binding.tvWalkCount.text = "${item.walkCount} 걸음"
            binding.tvRate.text = "${item.rank} 등"

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HubUserSearchViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = dataList
                } else {
                    val resultList = ArrayList<UserRankRvData>()
                    for (row in dataList) {
                        if (row.name.contains(charSearch.lowercase(Locale.ROOT))) {
                            Log.d(ContentValues.TAG, "performFiltering: contain")
                            resultList.add(row)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<UserRankRvData>
                notifyDataSetChanged()
            }

        }
    }
}