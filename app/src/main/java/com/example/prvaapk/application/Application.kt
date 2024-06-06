package com.example.prvaapk.application

import android.app.Application
import com.example.prvaapk.data.ApiService
import com.example.prvaapk.data.Repository

class MainApplication : Application() {
    companion object {
        val repository: Repository by lazy { Repository(ApiService.create()) }
    }
}
