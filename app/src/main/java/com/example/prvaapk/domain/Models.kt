package com.example.prvaapk.domain

data class Team(val idTeam: String, val strTeam: String, val strStadium: String, val strTeamBadge:String)
data class Match(val idEvent: String, val strEvent: String, val dateEvent: String, val intHomeScore:Int, val intAwayScore:Int, val strAwayTeamBadge:String, val strHomeTeamBadge:String)
data class League(val idLeague: String, val strLeague: String, val strSport: String, val strLeagueAlternate: String)
data class Season(val strSeason: String)
data class TableEntry(val idStanding: String, val intRank: String, val idTeam: String, val strTeam: String, val strTeamBadge: String, val idLeague: String, val strLeague: String, val strSeason: String, val strForm: String, val strDescription: String, val intPlayed: String, val intWin: String, val intLoss: String, val intDraw: String, val intGoalsFor: String, val intGoalsAgainst: String, val intGoalDifference: String, val intPoints: String, val dateUpdated: String)

data class LeagueResponse(val leagues: List<League>?)
data class SeasonResponse(val seasons: List<Season>?)
data class TableResponse(val table: List<TableEntry>?)

data class TeamResponse(val teams: List<Team>?)
data class MatchResponse(val results: List<Match>?)
