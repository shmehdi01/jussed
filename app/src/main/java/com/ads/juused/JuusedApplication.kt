package com.ads.juused

import android.app.Application
import android.content.Context
import com.ads.juused.network.DataManager

class JuusedApplication : Application() {
    companion object {
        private lateinit var context: Context
        private lateinit var mInstance: JuusedApplication

        fun getContext(): Context {
            return context
        }

        @Synchronized
        fun getInstance(): JuusedApplication {
            return mInstance
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        mInstance = this
        val dataManager: DataManager =
            DataManager.initializeDataManager()
        dataManager.initApiManager()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        System.gc()
    }
}