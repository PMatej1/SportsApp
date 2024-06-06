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


@Composable
fun TeamList(teams: List<Team>, onTeamClick: (Team) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(teams) { team ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onTeamClick(team) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = team.strTeam, style = MaterialTheme.typography.headlineSmall)
                    Text(text = team.strStadium, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun MatchList(matches: List<Match>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(matches) { match ->
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
fun TeamListScreen(navController: NavController) {
    val viewModel: MainViewModel = viewModel()
    val teamsState = viewModel.teams.observeAsState(initial = emptyList())
    val teams: List<Team>? = teamsState.value

    if (teams.isNullOrEmpty()) {
        Log.d("TeamListScreen", "No teams available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("TeamListScreen", "Teams available: $teams")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Teams") }) }
        ) {
            TeamList(teams) { team ->
                navController.navigate("matchList/${team.idTeam}")
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
    val matches: List<Match>? = matchesState.value

    if (matches.isNullOrEmpty()) {
        Log.d("MatchListScreen", "No matches available, showing loading indicator")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Log.d("MatchListScreen", "Matches available: $matches")
        Scaffold(
            topBar = { TopAppBar(title = { Text("Matches") }) }
        ) {
            MatchList(matches)
        }
    }
}

@Preview
@Composable
fun PreviewTeamList() {
    TeamList(
        listOf(Team("1", "Team 1", "Stadium 1"))
    ) {}
}

@Preview
@Composable
fun PreviewMatchList() {
    MatchList(
        listOf(Match("1", "Match 1", "Date 1"))
    )
}
