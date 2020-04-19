package com.example.franklymedia.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.franklymedia.R
import com.example.franklymedia.database.entity.RandomDogEntity
import com.example.franklymedia.viewholders.RandomDogViewHolder

class RecentDogsAdapter : RecyclerView.Adapter<RandomDogViewHolder>() {
    var mRecentDogList: List<RandomDogEntity>? = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomDogViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recent_dogs_row, parent, false)
        return RandomDogViewHolder(view)
    }

    fun updateDogsList(recentDogList: ArrayList<RandomDogEntity>) {
        mRecentDogList = recentDogList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mRecentDogList?.size!!
    }

    override fun onBindViewHolder(holder: RandomDogViewHolder, position: Int) {
        holder.bind(mRecentDogList?.get(position)!!)
    }
}