package com.example.franklymedia.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.franklymedia.R
import com.example.franklymedia.database.RandomDogDatabase
import com.example.franklymedia.utils.DebounceHandler
import com.example.franklymedia.utils.IFragmentCallBack
import kotlinx.android.synthetic.main.custom_tool_bar.*

abstract class BaseFragment : Fragment() {
    var fragmentCallback: IFragmentCallBack? = null
    var randomDogDB: RandomDogDatabase? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is IFragmentCallBack)
            fragmentCallback = context
        else
            throw RuntimeException("$context must implement FragmentCallback methods")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        randomDogDB = RandomDogDatabase.getDatabase(this.context!!)
    }

    /**
     * setting custom tollbar
     * title: passing screen name from previous fragment
     */
    fun setCustomActionBar(title: String?) {
        custom_tool_bar_ll_main_container?.visibility = View.VISIBLE
        custom_tool_bar_ll_main_container?.setBackgroundColor(ContextCompat.getColor(activity!!, R.color.white))

        custom_tool_bar_iv_back_image?.visibility = View.VISIBLE
        custom_tool_bar_iv_back_image?.setImageDrawable(ContextCompat.getDrawable(activity!!, R.drawable.ic_left_arrow))

        custom_tool_bar_tv_title?.isEnabled = false

        custom_tool_bar_tv_title?.text = title!!
        custom_tool_bar_iv_back_image?.setOnClickListener {
            DebounceHandler.handle(Runnable {
                activity?.onBackPressed()
            })
        }
    }

    override fun onDetach() {
        super.onDetach()
        if (null != fragmentCallback)
            fragmentCallback = null
    }
}