package com.example.myanimelistcleanarchitecture.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}