package com.example.franklymedia

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig

class MainApplication : Application() {
    companion object {
        var mApplicationInstance: MainApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationInstance = this

        /**
         * initializing fresco image library
         */
        val frescoConfig: ImagePipelineConfig = ImagePipelineConfig.newBuilder(this).build()
        Fresco.initialize(this, frescoConfig)
    }
}