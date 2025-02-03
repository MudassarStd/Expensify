package com.std.composeexpensetracker

import android.app.Application
import com.std.composeexpensetracker.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(databaseModule)
        }
    }
}