package com.bignerdranch.android.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CrimeListViewModel : ViewModel() {
//    var crimes = mutableListOf<Crime>()
    var crimeListLiveData: LiveData<List<Crime>> = MutableLiveData()

    init {
//        for(i in 0 until 100) {
//            val crime = Crime()
//            crime.title = "Crime#$i"
//            crime.isSolved = i % 2 == 0
//            crimes.add(crime)
//        }
        crimeListLiveData = CrimeRepository.get().getCrimes()
    }
}