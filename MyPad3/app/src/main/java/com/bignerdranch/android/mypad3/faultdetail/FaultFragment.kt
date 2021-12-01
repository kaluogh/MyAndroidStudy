package com.bignerdranch.android.mypad3.faultdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.bignerdranch.android.mypad3.R
import com.bignerdranch.android.mypad3.databinding.FaultFragmentBinding
import java.util.*

class FaultFragment : Fragment() {

    companion object {
        fun newInstance() = FaultFragment()
    }

    private lateinit var viewModel: FaultViewModel
    private lateinit var faultBinding: FaultFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        faultBinding = DataBindingUtil.inflate(inflater, R.layout.fault_fragment, container, false)
        return faultBinding.root
//        return inflater.inflate(R.layout.fault_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaultViewModel::class.java)
        // TODO: Use the ViewModel
        val testFault: Fault = getTestData()
        viewModel.fault = testFault
        faultBinding.viewModel = viewModel
        faultBinding.lifecycleOwner = this
        val faultFragmentAdapt = FaultFragmentAdapt(testFault.faultOperateList, layoutInflater, this)
        faultBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = faultFragmentAdapt
        }
    }

    private fun getTestData() : Fault{
        val faultOperate1 = FaultOperate()
        faultOperate1.title = "V=0  操作提示："
        faultOperate1.textList = listOf(
            "1.确认故障车辆所有车门门均已关闭并锁闭",
            "2.操作司机室右边柜故障面板上的[车门环路旁路]开关到[关]位，维持运行。",
            "3.如换端，则须将另一端司机室右边柜故障面板上的[车门环路旁路]开关置于到[关]位。",
        )
        val faultOperate2 = FaultOperate()
        faultOperate2.title = "V=1  操作提示："
        faultOperate2.textList = listOf(
            "1.确认故障车辆所有车门门均已关闭并锁闭",
            "2.操作司机室右边柜故障面板上的[车门环路旁路]开关到[关]位，维持运行。",
            "3.如换端，则须将另一端司机室右边柜故障面板上的[车门环路旁路]开关置于到[关]位。",
        )
//        val faultOperate3 = FaultOperate()
//        faultOperate3.title = "V=2  操作提示："
//        faultOperate3.textList = listOf(
//            "1.确认故障车辆所有车门门均已关闭并锁闭",
//            "2.操作司机室右边柜故障面板上的[车门环路旁路]开关到[关]位，维持运行。",
//            "3.如换端，则须将另一端司机室右边柜故障面板上的[车门环路旁路]开关置于到[关]位。",
//            "4.确认故障车辆所有车门门均已关闭并锁闭",
//            "5.操作司机室右边柜故障面板上的[车门环路旁路]开关到[关]位，维持运行。",
//            "6.如换端，则须将另一端司机室右边柜故障面板上的[车门环路旁路]开关置于到[关]位。",
//            "7.确认故障车辆所有车门门均已关闭并锁闭",
//            "8.操作司机室右边柜故障面板上的[车门环路旁路]开关到[关]位，维持运行。",
//            "9.如换端，则须将另一端司机室右边柜故障面板上的[车门环路旁路]开关置于到[关]位。",
//        )
        val faultOperates: List<FaultOperate> = listOf(faultOperate1, faultOperate2)
        return Fault(
            UUID.randomUUID(),
            "0002",
            "04",
            "10A2",
            2,
            "车门安全环路未建立",
            DateFormat.format("yyyy-MM-dd HH:mm:ss",Date()).toString(),
            faultOperates,
        )
    }
}