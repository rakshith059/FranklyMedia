package com.example.franklymedia.viewholders

import android.provider.SyncStateContract
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.franklymedia.R
import com.example.franklymedia.database.entity.RandomDogEntity
import com.example.franklymedia.utils.WIDTH_PERCENTAGE_75
import com.example.franklymedia.utils.calculateScreenWidth
import com.example.franklymedia.utils.logdExt
import com.facebook.drawee.view.SimpleDraweeView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RandomDogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var ivRecentDog: SimpleDraweeView? = null

    init {
        ivRecentDog = itemView.findViewById(R.id.recent_dogs_row_iv_image)

        val imageWidth = calculateScreenWidth(itemView.context, WIDTH_PERCENTAGE_75)
        ivRecentDog?.layoutParams?.width = imageWidth
        ivRecentDog?.requestLayout()
    }

    fun bind(mRecentDogList: RandomDogEntity) {
        if (mRecentDogList.dogImage != null)
            Observable.just(mRecentDogList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    logdExt("reandom dogs in viewholder === ${it.dogImage}")
                    ivRecentDog?.setImageURI(mRecentDogList.dogImage)
                }
    }
}