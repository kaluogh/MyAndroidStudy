package com.bignerdranch.android.beatbox

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class SoundViewModel : BaseObservable(){
    var sound: Sound? = null
        set(value) {
            field = value
            notifyChange()
        }

    @get:Bindable
    val title: String?
        get() = sound?.name
}