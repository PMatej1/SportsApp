package com.example.prvaapk.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.prvaapk.application.MainApplication
import com.example.prvaapk.data.Repository
import com.example.prvaapk.domain.Team
import com.example.prvaapk.domain.Match

class MainViewModel(private val repository: Repository = MainApplication.repository) : ViewModel() {
    val teams = liveData<List<Team>> { emit(repository.getTeams()) }
    fun getMatches(idTeam: String) = liveData<List<Match>> { emit(repository.getMatches(idTeam)) }
}
