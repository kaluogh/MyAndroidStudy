package com.bignerdranch.android.criminalintent

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.ContactsContract
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
import java.util.*


class CrimeFragment : Fragment(), DatePickerFragment.Callbacks {

    companion object {
        private const val TAG = "CrimeFragment"
        private const val  ARG_CRIME_ID = "crime_id"
        private const val DIALOG_DATE = "DIALOG_DATE"
        private const val REQUEST_DATE = 0
        private const val REQUEST_CONTACT = 1

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
    private lateinit var suspectButton: Button
    private lateinit var reportButton: Button

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
//        dateButton.apply {
//            text = crime.date.toString()
//            isEnabled = false
//        }
        solvedCheckBox = view.findViewById(R.id.crime_solved_checkbox)
        suspectButton = view.findViewById(R.id.crime_suspect_text)
        reportButton = view.findViewById(R.id.crime_report_text)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
        when{
            resultCode != Activity.RESULT_OK ->  return
            requestCode ==  REQUEST_CONTACT && data != null -> {
                val contractUri: Uri = data.data ?: Uri.EMPTY
                val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                val cursor = requireActivity().contentResolver.query(contractUri, queryFields, null, null, null)
                cursor?.use {
                    if (it.count == 0) {
                        return
                    }
                    it.moveToFirst()
                    val suspect = it.getString(0)
                    crime.suspect = suspect
                    viewModel.saveCrime(crime)
                    suspectButton.text = suspect
                }
            }
        }
    }

    private fun updateUI(){
        titleEditText.setText(crime.title)
        dateButton.text = crime.date.toString()
        solvedCheckBox.isChecked = crime.isSolved
        if (crime.suspect.isNotEmpty()) {
            suspectButton.text = crime.suspect
        } else {
            suspectButton.text = getString(R.string.crime_suspect_text)
        }
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

        dateButton.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply{
                setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                show(this@CrimeFragment.requireFragmentManager() ,DIALOG_DATE)
            }
        }

        reportButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type="text/plain"
                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.crime_report_subject))
            }.also { intent ->  
//                startActivity(intent)
                val chooserIntend =  Intent.createChooser(intent, getString(R.string.send_report))
                startActivity(chooserIntend)
            }
        }

        suspectButton.apply {
            val pickContactsContract = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
            setOnClickListener {
                startActivityForResult(pickContactsContract, REQUEST_CONTACT)
            }

            val resolveActivity: ResolveInfo? = requireActivity().packageManager.resolveActivity(pickContactsContract, PackageManager.MATCH_DEFAULT_ONLY)
            if (resolveActivity == null) {
                isEnabled = false
            }
        }

    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }

    override fun onStop() {
        super.onStop()
        viewModel.saveCrime(crime)
    }

    private fun getCrimeReport(): String {
        val solvedString = if (crime.isSolved) {
            getString(R.string.crime_report_solved)
        } else {
            getString(R.string.crime_report_unsolved)
        }

        val dateString = android.text.format.DateFormat.format("EEE, MMM, dd", crime.date)


        val suspect = if (crime.suspect.isBlank()){
            getString(R.string.crime_report_no_suspect)
        }else {
            getString(R.string.crime_report_suspect, crime.suspect)
        }

        return getString(R.string.crime_report, crime.title, dateString, solvedString, suspect)
    }
}