package com.valdizz.myapplication

import android.app.Application
import com.valdizz.myapplication.di.AppComponent
import com.valdizz.myapplication.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        component = DaggerAppComponent.create()
    }

    fun getAppComponent(): AppComponent {
        return component
    }

    companion object {
        lateinit var instance: App private set
    }
}