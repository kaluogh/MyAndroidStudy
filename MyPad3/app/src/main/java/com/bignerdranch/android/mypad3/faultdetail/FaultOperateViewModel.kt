package com.bignerdranch.android.mypad3.faultdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FaultOperateViewModel : ViewModel() {
    var faultOperate: FaultOperate? = null
        set(faultOperate) {
            field = faultOperate
            title.postValue(faultOperate?.title)
            textList.postValue(formatTextList(faultOperate?.textList))
        }

    var title: MutableLiveData<String?> = MutableLiveData()
    var textList: MutableLiveData<String?> = MutableLiveData()

    private fun formatTextList(textList: List<String>?): String{
        return if (textList == null) {
            ""
        } else {
            var result = ""
            textList.forEach { item ->
                result = result + item + "\n"
            }
            result.substring(0, result.length - 1)
        }
    }
}