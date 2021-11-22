package com.bignerdranch.android.beatbox

class Sound(var assetPath: String) {
    val name:String = assetPath.split("/").last().removeSuffix(".wav")
}