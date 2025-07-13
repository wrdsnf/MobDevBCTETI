package com.example.crud

import android.app.Application
import com.example.crud.di.AppModule
import com.example.crud.di.AppModuleImpl

class MainApplication: Application() {
    companion object{
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl()
    }
}