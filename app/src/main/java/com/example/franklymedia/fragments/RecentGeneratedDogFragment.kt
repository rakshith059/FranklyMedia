package com.example.franklymedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.franklymedia.R
import com.example.franklymedia.adapters.RecentDogsAdapter
import com.example.franklymedia.utils.DebounceHandler

class RecentGeneratedDogFragment : BaseFragment(), View.OnClickListener {
    var recentDogsAdapter: RecentDogsAdapter? = null
    var llDogList: LinearLayoutCompat? = null
    var rvDogList: RecyclerView? = null
    var btnClearDogs: AppCompatButton? = null
    var tvMessage: AppCompatTextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recent_dog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        llDogList = view?.findViewById(R.id.fragment_recent_dog_ll_list)
        rvDogList = view?.findViewById(R.id.fragment_recent_dog_rv_list)
        btnClearDogs = view?.findViewById(R.id.fragment_recent_dog_btn_clear_dogs)
        tvMessage = view?.findViewById(R.id.fragment_recent_dog_tv_message)

        activity?.actionBar?.hide()
        setCustomActionBar(resources.getString(R.string.recent_dogs_text))

        recentDogsAdapter = RecentDogsAdapter()
        getRandomDogsFromRoom()

        /**
         * LinearSnapHelper : snap the center of the target child view to the center of the attached RecyclerView
         */
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(rvDogList)

        rvDogList?.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rvDogList?.setRecycledViewPool(RecyclerView.RecycledViewPool())

    }

    private fun getRandomDogsFromRoom() {
        val randomDogList = randomDogDB?.getRandomDogDao()?.getAllRandomDogs()
        if (randomDogList?.isNotEmpty()!!) {
            llDogList?.visibility = View.VISIBLE
            tvMessage?.visibility = View.GONE

            recentDogsAdapter?.updateDogsList(ArrayList(randomDogList.reversed()))
            recentDogsAdapter?.notifyDataSetChanged()
            rvDogList?.adapter = recentDogsAdapter
        } else {
            llDogList?.visibility = View.GONE
            tvMessage?.visibility = View.VISIBLE
        }
    }

    override fun onClick(v: View?) {
        DebounceHandler.handle(Runnable {
            when (v?.id) {
                R.id.fragment_recent_dog_btn_clear_dogs -> {
                    randomDogDB?.getRandomDogDao()?.deleteAllRandomDogs()
                    getRandomDogsFromRoom()
                }
            }
        })
    }
}