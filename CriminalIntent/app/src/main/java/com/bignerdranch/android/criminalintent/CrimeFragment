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

private const val TAG = "CrimeFragment"

class CrimeFragment : Fragment() {

    companion object {
        fun newInstance() = CrimeFragment()
    }

    private lateinit var crime: Crime
    private lateinit var viewModel: CrimeViewModel
    private lateinit var titleEditText: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.crime_fragment, container, false)
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
        // TODO: Use the ViewModel
    }

    override fun onStart() {
        super.onStart()

        var titleWacher = object : TextWatcher {
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
        titleEditText.addTextChangedListener(titleWacher)

//        solvedCheckBox.setOnCheckedChangeListener{ _, isChecked ->
//            crime.isSolved = isChecked
//        }

        var solvedListener = object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
                crime.isSolved = isChecked
                Log.d(TAG, "isChecked: " + isChecked.toString())
            }
        }
        solvedCheckBox.setOnCheckedChangeListener(solvedListener)
    }

}