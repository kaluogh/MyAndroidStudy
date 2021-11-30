package com.bignerdranch.android.mypad3.faultdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class FaultViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    var fault: Fault? = null
        set(fault) {
            field = fault
            id.postValue(fault?.id)
            group.postValue(fault?.group)
            number.postValue(fault?.number)
            code.postValue(formatCode(fault?.code))
            level.postValue(fault?.level)
            description.postValue(fault?.description)
            createTime.postValue(fault?.createTime)
            faultOperateList.postValue(fault?.faultOperateList)
        }

    var id: MutableLiveData<UUID?> = MutableLiveData()
    var group: MutableLiveData<String?> = MutableLiveData()
    var number: MutableLiveData<String?> = MutableLiveData()
    var code: MutableLiveData<String?> = MutableLiveData()
    var level: MutableLiveData<Int?> = MutableLiveData()
    var description: MutableLiveData<String?> = MutableLiveData()
    var createTime: MutableLiveData<String?> = MutableLiveData()
    var faultOperateList: MutableLiveData<List<FaultOperate>?> = MutableLiveData()

    private fun formatCode(code: String?) :String {
//        return code.toString().uppercase()

//        return code?.uppercase() ?: ""
        return when (code) {
            null -> {
                ""
            }
            else -> {
                code.uppercase()
            }
        }
    }
}