package com.bignerdranch.android.mypad3.faultdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.mypad3.R
import com.bignerdranch.android.mypad3.databinding.FaultOperateBinding

class FaultFragmentAdapt(
    private val faultOperates: List<FaultOperate>?,
    private val layoutInflater: LayoutInflater,
    private val owener: FaultFragment
    ) :
    RecyclerView.Adapter<FaultFragmentHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaultFragmentHolder {
        val binding = DataBindingUtil.inflate<FaultOperateBinding>(
            layoutInflater,
            R.layout.fault_operate,
            parent,
            false
        )
        binding.lifecycleOwner = owener
        return FaultFragmentHolder(binding)
    }

    override fun onBindViewHolder(holder: FaultFragmentHolder, position: Int) {
        if (faultOperates != null) {
            val item = faultOperates[position]
            holder.bind(item)
        }
    }

    override fun getItemCount(): Int {
        return faultOperates?.size ?: 0
    }
}