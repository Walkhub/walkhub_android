package com.semicolon.walkhub.adapter

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
    fun setLifeCycleOwner(lifecycleOwner: LifecycleOwner) {
        binding.lifecycleOwner = lifecycleOwner
    }
}