package com.bignerdranch.android.mypad3.faultdetail

import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.mypad3.databinding.FaultOperateBinding

class FaultFragmentHolder(private val binding: FaultOperateBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.viewModel = FaultOperateViewModel()
    }

    fun bind(faultOperate: FaultOperate){
        binding.apply {
            viewModel?.faultOperate = faultOperate
//            executePendingBindings()
        }
        binding.notifyChange()
    }
}