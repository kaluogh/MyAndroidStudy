package com.bignerdranch.android.mypad3.faultdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.format.DateFormat
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
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
        viewModel.fault = Fault(
            UUID.randomUUID(),
            "0002",
            "04",
            "10A2",
            2,
            "车门安全环路未建立",
            DateFormat.format("yyyy-MM-dd HH:mm:ss",Date()).toString(),
            emptyList(),
        )
        faultBinding.viewModel = viewModel
        faultBinding.lifecycleOwner = this
    }
}