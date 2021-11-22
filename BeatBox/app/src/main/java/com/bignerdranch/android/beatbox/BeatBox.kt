package com.bignerdranch.android.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

class BeatBox(private var assets: AssetManager) {
    companion object{
        private const val TAG = "BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    val sounds: List<Sound>

    init {
        sounds = this.loadSounds()
    }

    private fun loadSounds(): List<Sound> {
        val soundNames: Array<String>

        try {
            soundNames = assets.list(SOUNDS_FOLDER)!!
            Log.d(TAG, "Found ${soundNames.size} sounds.")
        }catch (e: Exception) {
            Log.d(TAG, "Could not list assets.", e)
            return emptyList()
        }

        val sounds = mutableListOf<Sound>()
        soundNames.forEach { item ->
            sounds.add(Sound("$SOUNDS_FOLDER/$item"))
        }
        return sounds
    }

}