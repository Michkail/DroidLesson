package com.tdsoft.myapplication

import android.app.Application
import retrofit2.Retrofit
import retrofit2.create

class MyApp : Application() {
    companion object{
        private lateinit var instance: MyApp
        fun  getInstance(): MyApp = instance
    }

    private var retrofit: Retrofit? = null
    override fun onCreate() {
        super.onCreate()
        instance = this

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.43.51:8000")
            .build()
    }

    fun getApiServices(): APIServices? = retrofit?.create(APIServices::class.java)
}