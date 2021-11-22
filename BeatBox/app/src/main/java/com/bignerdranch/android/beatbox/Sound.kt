package com.bignerdranch.android.beatbox

class Sound(var assetPath: String, var soundId: Int? = null) {
    val name:String = assetPath.split("/").last().removeSuffix(".wav")
}