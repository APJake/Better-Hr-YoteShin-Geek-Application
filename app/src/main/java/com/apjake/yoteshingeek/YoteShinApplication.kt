package com.apjake.yoteshingeek

import android.app.Application
import com.apjake.yoteshingeek.di.AppComponent
import com.apjake.yoteshingeek.di.DaggerAppComponent

open class YoteShinApplication: Application() {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}