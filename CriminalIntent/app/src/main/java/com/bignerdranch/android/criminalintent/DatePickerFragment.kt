package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class DatePickerFragment: DialogFragment() {

    interface Callbacks{
        fun onDateSelected(date: Date)
    }

    companion object{
        private const val ARG_DATE = "ARG_DATE"

        fun newInstance(date: Date): DatePickerFragment{
            val args = Bundle().apply{
                putSerializable(ARG_DATE, date)
            }
            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        return super.onCreateDialog(savedInstanceState)
        val dateListener = DatePickerDialog.OnDateSetListener{ _: DatePicker, year:Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            targetFragment?.let { fragment ->  
                (fragment as Callbacks).onDateSelected(resultDate)
            }
        }

        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(
            requireContext(),
            dateListener,
            year,
            month,
            day,
        )

    }
}