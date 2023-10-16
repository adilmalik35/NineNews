package com.nine.ninenews


import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NineNewsApp : Application(){

    override fun onCreate() {
        super.onCreate()
    }
}