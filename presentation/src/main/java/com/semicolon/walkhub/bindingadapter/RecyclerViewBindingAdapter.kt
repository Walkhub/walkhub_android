package com.semicolon.walkhub.bindingadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.semicolon.walkhub.adapter.RecyclerViewAdapter
import com.semicolon.walkhub.adapter.RecyclerViewItem

@BindingAdapter("recyclerItems")
fun RecyclerView.setList(list: List<RecyclerViewItem>?) {
    if (adapter == null) {
        adapter = RecyclerViewAdapter()
        layoutManager = LinearLayoutManager(context)
    }

    if (list != null) {
        (adapter as? RecyclerViewAdapter)?.updateData(list)
    }
}

@BindingAdapter("horizontalRecyclerItems")
fun RecyclerView.setHorizontalList(list: List<RecyclerViewItem>?) {
    if (adapter == null) {
        adapter = RecyclerViewAdapter()
        layoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
    }

    if (list != null) {
        (adapter as? RecyclerViewAdapter)?.updateData(list)
    }
}