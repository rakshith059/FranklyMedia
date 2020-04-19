package com.example.franklymedia.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.franklymedia.R
import com.example.franklymedia.database.entity.RandomDogEntity
import com.example.franklymedia.models.RandomDogModel
import com.example.franklymedia.utils.DebounceHandler
import com.example.franklymedia.utils.isNetworkAvailable
import com.example.franklymedia.utils.logdExt
import com.example.franklymedia.viewmodels.LiveDataWrapper
import com.example.franklymedia.viewmodels.RandomDogViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_generate_random_dog.*

class GenerateRandomDogFragment : BaseFragment() {
    var randomDogViewModel: RandomDogViewModel? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_generate_random_dog, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setCustomActionBar(resources.getString(R.string.generate_dogs_text))

        val randomDogFactory = RandomDogViewModel.Factory()
        randomDogViewModel = ViewModelProviders.of(this, randomDogFactory).get(RandomDogViewModel::class.java)

        /**
         * since we have only one button, not implementing View.OnCLickListner
         * Directly using click functionality
         */
        fragment_generate_random_dog_btn_generate.setOnClickListener {
            DebounceHandler.handle(Runnable {
                if (isNetworkAvailable(this.context!!)) {
                    randomDogViewModel?.getRandomDog()
                } else {
                    Snackbar.make(fragment_generate_random_dog_ll_main_container, resources.getString(R.string.err_no_internet), Snackbar.LENGTH_LONG).show()
                }

                fragment_generate_random_dog_pb_progress.visibility = View.VISIBLE
            })
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        /**
         * For avoid leaking Livedata observers.
         * using viewLifecycleOwner, so that Livedata will remove observers every time the fragment's view is destroyed
         */
        randomDogViewModel?.getLiveDataObservable()?.observe(viewLifecycleOwner, Observer<LiveDataWrapper<RandomDogModel>> {
            try {
                fragment_generate_random_dog_pb_progress.visibility = View.GONE

                if (null != it.data) {
                    logdExt("response from getRandomDog API === ${it.data}")
                    val randomDogImage = it.data?.randomDogImage
                    if (null != randomDogImage) {
                        fragment_generate_random_dog_iv_image?.setImageURI(randomDogImage)

                        val randomDogsList = randomDogDB?.getRandomDogDao()?.getAllRandomDogs()
                        val randomDogsTotalSize = randomDogsList?.size!!

                        /**
                         * Check the database size
                         * if it exceeds limit then delete the LRU item from table to make way to the latest item
                         */
                        if (randomDogsTotalSize >= 20)
                            randomDogDB?.getRandomDogDao()?.deleteLRURandomDog()

                        /**
                         * if randomDog image is present then insert that item to Room DB
                         * Checking for the last item because Observer will be triggered on screen rotation, and to avoid multiple entries in db
                         */
                        if (randomDogsList.isNotEmpty() && !randomDogsList.get(randomDogsTotalSize - 1).dogImage.equals(randomDogImage))
                            randomDogDB?.getRandomDogDao()?.insertRandomDog(RandomDogEntity(dogImage = randomDogImage))
                        else
                            randomDogDB?.getRandomDogDao()?.insertRandomDog(RandomDogEntity(dogImage = randomDogImage))
                    }
                } else {
                    if (it.error is Throwable) {
                        logdExt("error from getRandomDog API === ${it.error}")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
    }
}