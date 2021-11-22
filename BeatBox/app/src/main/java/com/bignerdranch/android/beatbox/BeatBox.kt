package com.bignerdranch.android.beatbox

import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.media.SoundPool
import android.util.Log
import java.lang.Exception

class BeatBox(private var assets: AssetManager) {
    companion object{
        private const val TAG = "BeatBox"
        private const val SOUNDS_FOLDER = "sample_sounds"
    }

    val sounds: List<Sound>
    private val soundPool = SoundPool.Builder().setMaxStreams(5).build()

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
            val sound = Sound("$SOUNDS_FOLDER/$item")
            load(sound)
            sounds.add(sound)
        }
        return sounds
    }

    private fun load(sound: Sound) {
        val afd:AssetFileDescriptor = assets.openFd(sound.assetPath)
        val soundId = soundPool.load(afd, 1)
        sound.soundId = soundId
    }

    fun play(sound: Sound) {
        sound.soundId?.let {
            soundPool.play(it, 1.0f, 1.0f,1, 0 , 1.0f)
        }
    }
}