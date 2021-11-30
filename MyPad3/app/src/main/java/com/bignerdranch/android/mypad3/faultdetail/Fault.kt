package com.bignerdranch.android.mypad3.faultdetail

import java.util.*

data class Fault(
    var id: UUID? = null,
    var number: String? = null,
    var code: String? = null,
    var level: Int? = null,
    var description: String? = null,
    var createTime: Date? = null,
    var faultOperateList: List<FaultOperate>? = null,
)
