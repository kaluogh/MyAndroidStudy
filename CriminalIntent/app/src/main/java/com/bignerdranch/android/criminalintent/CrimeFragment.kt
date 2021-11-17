package com.bignerdranch.android.criminalintent

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import androidx.lifecycle.Observer
import java.util.*


class CrimeFragment : Fragment() {

    companion object {
        private const val TAG = "CrimeFragment"
        private const val  ARG_CRIME_ID = "crime_id"

        fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }

    private lateinit var crimeId:UUID
    private lateinit var crime: Crime
    private lateinit var viewModel: CrimeViewModel
    private lateinit var titleEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
        crimeId = arguments?.getSerializable(ARG_CRIME_ID) as UUID
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.crime_fragment, container, false)
        titleEditText = view.findViewById(R.id.crime_title_edittext)
        dateButton = view.findViewById(R.id.crime_date_button)
        dateButton.apply {
            text = crime.date.toString()
            isEnabled = false
        }
        solvedCheckBox = view.findViewById(R.id.crime_solved_checkbox)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CrimeViewModel::class.java)
        viewModel.loadCrime(crimeId)
        viewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            { crime ->
                crime?.let {
                    this.crime = crime
                    updateUI()
                }
            }
        )
        // TODO: Use the ViewModel
    }

    private fun updateUI(){
        titleEditText.setText(crime.title)
        dateButton.text = crime.date.toString()
        solvedCheckBox.isChecked = crime.isSolved
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
//                TODO("Not yet implemented")
            }
        }
        titleEditText.addTextChangedListener(titleWatcher)

//        solvedCheckBox.setOnCheckedChangeListener{ _, isChecked ->
//            crime.isSolved = isChecked
//        }

        val solvedListener = CompoundButton.OnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
            Log.d(TAG, "isChecked: $isChecked")
        }
        solvedCheckBox.setOnCheckedChangeListener(solvedListener)
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveCrime(crime)
    }
}