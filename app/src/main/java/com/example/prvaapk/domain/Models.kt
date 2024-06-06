package com.example.prvaapk.domain

data class Team(val idTeam: String, val strTeam: String, val strStadium: String)
data class Match(val idEvent: String, val strEvent: String, val dateEvent: String)
data class TeamResponse(val teams: List<Team>?)
data class MatchResponse(val events: List<Match>?)
