package com.bignerdranch.android.beatbox

class Sound(var assetPath: String) {

    init {
        val name = assetPath.split("/").last().removeSuffix(".wav")
    }
}