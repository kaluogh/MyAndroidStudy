package com.bignerdranch.android.mypadtest.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var label1:MutableLiveData<String?> = MutableLiveData()
    var label2:MutableLiveData<String?> = MutableLiveData()
    var text1:MutableLiveData<String?> = MutableLiveData()
    var text2:MutableLiveData<String?> = MutableLiveData()
}