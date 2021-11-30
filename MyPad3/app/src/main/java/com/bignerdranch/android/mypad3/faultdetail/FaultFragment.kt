package com.bignerdranch.android.mypad3.faultdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.mypad3.R

class FaultFragment : Fragment() {

    companion object {
        fun newInstance() = FaultFragment()
    }

    private lateinit var viewModel: FaultViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fault_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FaultViewModel::class.java)
        // TODO: Use the ViewModel
    }

}