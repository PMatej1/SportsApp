package com.example.prvaapk.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.prvaapk.application.MainApplication
import com.example.prvaapk.data.Repository
import com.example.prvaapk.domain.Team
import com.example.prvaapk.domain.Match

class MainViewModel(private val repository: Repository = MainApplication.repository) : ViewModel() {
    val teams = liveData<List<Team>> {
        try {
            emit(repository.getTeams())
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load teams", e)
        }
    }

    fun getMatches(idTeam: String) = liveData<List<Match>> {
        try {
            emit(repository.getMatches(idTeam))
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load matches", e)
        }
    }
}
