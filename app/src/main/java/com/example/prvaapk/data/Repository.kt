package com.example.prvaapk.data

import android.util.Log
import com.example.prvaapk.domain.League
import com.example.prvaapk.domain.Team
import com.example.prvaapk.domain.Match
import com.example.prvaapk.domain.Season
import com.example.prvaapk.domain.TableEntry
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
        val results = response.results
        Log.d("Repository", "Fetched matches: $results")
        if (results == null) {
            Log.e("Repository", "Failed to parse matches: response events are null")
            emptyList()
        } else {
            results
        }
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch matches", e)
        emptyList()
    }

    suspend fun getAllLeagues(): List<League> = try {
        val response = apiService.getAllLeagues()
        response.leagues?.filter { it.strSport == "Soccer" } ?: emptyList()
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch leagues", e)
        emptyList()
    }

    suspend fun getAllSeasons(leagueId: String): List<Season> = try {
        val response = apiService.getAllSeasons(leagueId)
        response.seasons?.filter { it.strSeason >= "2016-2017" }?.sortedByDescending { it.strSeason } ?: emptyList()
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch seasons", e)
        emptyList()
    }



    suspend fun getLeagueTable(leagueId: String, season: String): List<TableEntry> = try {
        val response = apiService.getLeagueTable(leagueId, season)
        response.table ?: emptyList()
    } catch (e: IOException) {
        Log.e("Repository", "Failed to fetch league table", e)
        emptyList()
    }
}
