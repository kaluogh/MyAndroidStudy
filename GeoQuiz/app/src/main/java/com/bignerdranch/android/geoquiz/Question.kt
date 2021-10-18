package com.bignerdranch.android.geoquiz

import androidx.annotation.StringRes

data class Question (@StringRes var textResId: Int,var answer: Boolean)