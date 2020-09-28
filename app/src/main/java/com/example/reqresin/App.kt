package com.example.reqresin

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.Context
import com.example.reqresin.dagger.AppComponent
import com.example.reqresin.dagger.AppModule
import com.example.reqresin.dagger.DaggerAppComponent

class App: Application() {
    private lateinit var applicationComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun component(): AppComponent {
        return applicationComponent
    }

    companion object {
        operator fun get(activity: Activity?): App {
            return activity?.application as? App ?: throw IllegalStateException()//wtf("Activity is null or context is not related to application context.")
        }

        operator fun get(service: Service): App {
            return service.application as? App ?: throw IllegalStateException()//wtf("Service context is not related to application context.")
        }

        operator fun get(context: Context?): App {
            return context?.applicationContext as? App ?:throw IllegalStateException()// wtf("Context is null or context is not related to application context.")
        }

        private fun wtf(s: String): App? {
            return null
        }


    }
}