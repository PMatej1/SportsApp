package com.example.prvaapk.data

import com.example.prvaapk.domain.Team
import com.example.prvaapk.domain.Match
import java.io.IOException

class Repository(private val apiService: ApiService) {
    suspend fun getTeams(): List<Team> = try {
        apiService.getTeams().teams
    } catch (e: IOException) {
        emptyList()
    }

    suspend fun getMatches(idTeam: String): List<Match> = try {
        apiService.getMatches(idTeam).events
    } catch (e: IOException) {
        emptyList()
    }
}
