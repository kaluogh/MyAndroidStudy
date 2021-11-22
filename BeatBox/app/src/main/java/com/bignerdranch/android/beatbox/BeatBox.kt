package com.bignerdranch.android.beatbox

import android.content.res.AssetManager
import android.util.Log
import java.lang.Exception

class BeatBox(private var assets: AssetManager) {
    companion object{
        private const val TAG = "BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    fun loadSounds(): List<String> {
        return try {
            val soundNames = assets.list(SOUNDS_FOLDER)
            if (soundNames != null) {
                Log.d(TAG, "Found ${soundNames.size} sounds.")
                soundNames.asList()
            } else{
                emptyList()
            }
        }catch (e: Exception) {
            Log.d(TAG, "Could not list assets.", e)
            emptyList()
        }
    }

}