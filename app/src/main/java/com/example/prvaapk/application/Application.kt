package com.example.prvaapk.application

import android.app.Application
import com.example.prvaapk.data.Repository
import com.example.prvaapk.data.SportsApi

class MainApplication : Application() {
    companion object {
        val repository: Repository by lazy { Repository(SportsApi.retrofitService) }
    }
}
