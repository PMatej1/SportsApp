package com.example.prvaapk.data

import android.util.Log
import com.example.prvaapk.domain.Team
import com.example.prvaapk.domain.Match
import java.io.IOException

class Repository(private val apiService: ApiService) {
    suspend fun getTeams(league: String = "English Premier League"): List<Team> = try {
        val response = apiService.getTeams(league)
        val teams = response.teams
        Log.d("Repository", "Fetched teams: $teams")
        if (teams == null) {
            Log.e("Repository", "Failed to parse teams: response teams are null")
            emptyList()
        } else {
            teams
        }
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch teams", e)
        emptyList()
    }

    suspend fun getMatches(idTeam: String = "133602"): List<Match> = try {
        val response = apiService.getMatches(idTeam)
        val matches = response.events
        Log.d("Repository", "Fetched matches: $matches")
        if (matches == null) {
            Log.e("Repository", "Failed to parse matches: response events are null")
            emptyList()
        } else {
            matches
        }
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch matches", e)
        emptyList()
    }
}
