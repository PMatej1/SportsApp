package com.example.prvaapk.presentation

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prvaapk.domain.Match
import com.example.prvaapk.domain.Team

import com.example.prvaapk.domain.League
import com.example.prvaapk.domain.Season
import com.example.prvaapk.domain.TableEntry

@Composable
fun LeagueList(leagues: List<League>, onLeagueClick: (League) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(leagues) { league ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onLeagueClick(league) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = league.strLeague, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}

@Composable
fun SeasonList(seasons: List<Season>, onSeasonClick: (Season) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(seasons) { season ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onSeasonClick(season) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = season.strSeason, style = MaterialTheme.typography.headlineSmall)
                }
            }
        }
    }
}

@Composable
fun TableList(table: List<TableEntry>, onRowClick: (TableEntry) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(table) { entry ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onRowClick(entry) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = entry.strTeam, style = MaterialTheme.typography.headlineSmall)
                    Text(text = "Points: ${entry.intPoints}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun MatchList(results: List<Match>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(results) { match ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = match.strEvent, style = MaterialTheme.typography.headlineSmall)
                    Text(text = match.dateEvent, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeagueListScreen(navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    val leaguesState = viewModel.getAllLeagues().observeAsState(initial = emptyList())
    val leagues: List<League>? = leaguesState.value

    if (leagues.isNullOrEmpty()) {
        Log.d("LeagueListScreen", "No leagues available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("LeagueListScreen", "Leagues available: $leagues")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Leagues") }) }
        ) {
            LeagueList(leagues) { league ->
                navController.navigate("seasonList/${league.idLeague}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeasonListScreen(leagueId: String?, navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    val seasonsState = viewModel.getAllSeasons(leagueId ?: "").observeAsState(initial = emptyList())
    val seasons: List<Season>? = seasonsState.value

    if (seasons.isNullOrEmpty()) {
        Log.d("SeasonListScreen", "No seasons available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("SeasonListScreen", "Seasons available: $seasons")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Seasons") }) }
        ) {
            SeasonList(seasons) { season ->
                navController.navigate("tableList/${leagueId}/${season.strSeason}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TableListScreen(leagueId: String?, season: String?, navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    val tableState = viewModel.getLeagueTable(leagueId ?: "", season ?: "").observeAsState(initial = emptyList())
    val table: List<TableEntry>? = tableState.value

    if (table.isNullOrEmpty()) {
        Log.d("TableListScreen", "No table available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("TableListScreen", "Table available: $table")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Table") }) }
        ) {
            TableList(table) { entry ->
                navController.navigate("matchList/${entry.idTeam}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MatchListScreen(teamId: String?) {
    val viewModel: MainViewModel = viewModel()
    val matchesState = viewModel.getMatches(teamId ?: "").observeAsState(initial = emptyList())
    val results: List<Match>? = matchesState.value

    if (results.isNullOrEmpty()) {
        Log.d("MatchListScreen", "No matches available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("MatchListScreen", "Matches available: $results")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Matches") }) }
        ) {
            MatchList(results)
        }
    }
}


@Preview
@Composable
fun PreviewLeagueList() {
    LeagueList(
        listOf(League("1", "League 1", "Soccer", "L1"))
    ) {}
}

@Preview
@Composable
fun PreviewSeasonList() {
    SeasonList(
        listOf(Season("2020-2021"))
    ) {}
}

@Preview
@Composable
fun PreviewTableList() {
    TableList(
        listOf(TableEntry("1", "1", "1", "Team 1", "https://example.com/badge.png", "1", "League 1", "2020-2021", "WLWLW", "Promotion - Champions League (Group Stage)", "38", "27", "6", "5", "83", "32", "51", "86", "2021-06-17 23:00:11"))
    ) {}
}

@Preview
@Composable
fun PreviewMatchList() {
    MatchList(
        listOf(Match("1", "Match 1", "Date 1"))
    )
}