package com.bignerdranch.android.mypad3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.mypad3.faultdetail.FaultFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val faultFragment = FaultFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container,faultFragment)
                .commit()
        }
    }
}