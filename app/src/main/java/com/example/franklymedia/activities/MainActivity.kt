package com.example.franklymedia.activities

import android.os.Bundle
import com.example.franklymedia.R
import com.example.franklymedia.fragments.HomeFragment
import com.example.franklymedia.utils.AnimStyleEnum

class MainActivity : BaseActivity() {
    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        if (isEmptyContainer())
            addFragment(HomeFragment(), TAG, AnimStyleEnum.SLIDE_RIGHT.name)
//        addFragment(GenerateRandomDogFragment(), tag, AnimStyleEnum.SLIDE_RIGHT.name)
    }

    //used to check for the fragment in the home container
    private fun isEmptyContainer(): Boolean {
        val fragmentManager = supportFragmentManager
        return fragmentManager.findFragmentById(R.id.home_container) == null
    }
}
