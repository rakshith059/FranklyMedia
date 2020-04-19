package com.example.franklymedia.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.franklymedia.R
import com.example.franklymedia.utils.SPLASH_SCREEN_DELAY_MILLI_SEC
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mCompositeDisposable.add(
            Observable.timer(SPLASH_SCREEN_DELAY_MILLI_SEC, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    val intent = Intent(this, MainActivity::class.java)
                    finish()
                    startActivity(intent)
                })
    }
}
