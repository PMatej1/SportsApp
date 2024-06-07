package com.example.prvaapk.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.prvaapk.application.MainApplication
import com.example.prvaapk.data.Repository
import com.example.prvaapk.domain.Match

class MainViewModel(private val repository: Repository = MainApplication.repository) : ViewModel() {

    fun getMatches(idTeam: String) = liveData<List<Match>> {
        try {
            emit(repository.getMatches(idTeam))
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load matches", e)
        }
    }

    fun getAllLeagues() = liveData {
        try {
            emit(repository.getAllLeagues())
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load leagues", e)
        }
    }

    fun getAllSeasons(leagueId: String) = liveData {
        try {
            emit(repository.getAllSeasons(leagueId))
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load seasons", e)
        }
    }

    fun getLeagueTable(leagueId: String, season: String) = liveData {
        try {
            emit(repository.getLeagueTable(leagueId, season))
        } catch (e: Exception) {
            Log.e("MainViewModel", "Failed to load league table", e)
        }
    }
}
