package com.example.franklymedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.franklymedia.R
import com.example.franklymedia.utils.AnimStyleEnum
import com.example.franklymedia.utils.DebounceHandler
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), View.OnClickListener {
    val TAG = HomeFragment::class.java.simpleName

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fragment_home_btn_generate_dogs.setOnClickListener(this)
        fragment_home_btn_recent_dogs.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        DebounceHandler.handle(Runnable {
            when (v?.id) {
                R.id.fragment_home_btn_generate_dogs -> fragmentCallback?.addFragment(GenerateRandomDogFragment(), TAG, AnimStyleEnum.SLIDE_RIGHT.name)
                R.id.fragment_home_btn_recent_dogs -> fragmentCallback?.addFragment(RecentGeneratedDogFragment(), TAG, AnimStyleEnum.SLIDE_RIGHT.name)
            }
        })
    }


}