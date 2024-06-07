package com.example.prvaapk.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.prvaapk.domain.Match
import com.example.prvaapk.domain.League
import com.example.prvaapk.domain.Season
import com.example.prvaapk.domain.TableEntry
import coil.compose.AsyncImage
import com.example.prvaapk.R


@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp >= 600
}


@Composable
fun LeagueList(leagues: List<League>, onLeagueClick: (League) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

            .padding(vertical = 60.dp)
            .background(color = Color(19, 109, 21))
    ) {
        items(leagues) { league ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)

                    .clickable { onLeagueClick(league) },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = league.strLeague, fontSize = 20.sp)

                }
            }
        }
    }
}

@Composable
fun TabletLeagueList(leagues: List<League>, onLeagueClick: (League) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 50.dp)
            .background(color = Color.Blue)
    ) {
        items(leagues) { league ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(color = Color.Yellow)
                    .clickable { onLeagueClick(league) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(text = league.strLeague, fontSize = 24.sp)
                }
            }
        }
    }
}



@Composable
fun SeasonList(seasons: List<Season>, onSeasonClick: (Season) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

            .padding(vertical = 60.dp)
            .background(color = Color(19, 109, 21))
    ) {
        items(seasons) { season ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onSeasonClick(season) },
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                 Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround)   {
                        Image(
                            painter = painterResource(id = R.drawable.vecteezy_simple_football_sport_icon_on_white_background_4693432),
                            contentDescription = null,
                            modifier = Modifier
                                .height(40.dp)
                                .width(40.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = season.strSeason,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TabletSeasonList(seasons: List<Season>, onSeasonClick: (Season) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(vertical = 50.dp)
    ) {
        items(seasons) { season ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onSeasonClick(season) },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Image(
                            painter = painterResource(id = R.drawable.vecteezy_simple_football_sport_icon_on_white_background_4693432),
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = season.strSeason,
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun TableList(table: List<TableEntry>, onRowClick: (TableEntry) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()

            .padding(vertical = 60.dp)
    ) {
        item{Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 45.dp, top = 20.dp)) {


            TableCell(text = "team", weight = 3f)
            TableCell(text ="GP" , weight = 1f)
            TableCell(text ="W" , weight = 1f)
            TableCell(text ="D" , weight = 1f)
            TableCell(text ="L" , weight = 1f)
            TableCell(text ="P" , weight = 1f)

        }}
        items(table) { entry ->
            /*Card*/ Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onRowClick(entry) }
                /*elevation = CardDefaults.cardElevation(4.dp)*/
            ) {
                /*Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(model = entry.strTeamBadge, contentDescription =null )

                    Text(text = entry.strTeam, style = MaterialTheme.typography.headlineSmall)

                    Text(text = "Points: ${entry.intPoints}. Played: ${entry.intPlayed}. Won: ${entry.intWin}. Draw: ${entry.intDraw}. Lost: ${entry.intLoss}", style = MaterialTheme.typography.bodyMedium)
                }*/
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(model = entry.strTeamBadge, contentDescription =null, modifier = Modifier.padding(end = 10.dp) )
                if (entry.strTeam.length>=10)
                    TableCell(text = entry.strTeam.substring(startIndex = 0, endIndex = 8), weight = 3f)
                else
                    TableCell(text = entry.strTeam, weight = 3f)
                TableCell(text =entry.intPlayed , weight = 1f)
                TableCell(text =entry.intWin , weight = 1f)
                TableCell(text =entry.intDraw , weight = 1f)
                TableCell(text =entry.intLoss , weight = 1f)
                TableCell(text =entry.intPoints , weight = 1f)

            }
            }
        }
    }

}

@Composable
fun TabletTableList(table: List<TableEntry>, onRowClick: (TableEntry) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(vertical = 50.dp)
    ) {
        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp)) {
                TableCell(text = "team", weight = 3f)
                TableCell(text = "GP", weight = 1.5f)
                TableCell(text = "W", weight = 1f)
                TableCell(text = "D", weight = 1f)
                TableCell(text = "L", weight = 1f)
                TableCell(text = "P", weight = 1f)
            }
        }
        items(table) { entry ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onRowClick(entry) }
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(end = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(model = entry.strTeamBadge, contentDescription = null)
                    TableCell(text = entry.strTeam, weight = 3f)
                    TableCell(text = entry.intPlayed, weight = 1f)
                    TableCell(text = entry.intWin, weight = 1f)
                    TableCell(text = entry.intDraw, weight = 1f)
                    TableCell(text = entry.intLoss, weight = 1f)
                    TableCell(text = entry.intPoints, weight = 1f)
                }
            }
        }
    }
}


@Composable
fun MatchList(results: List<Match>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(19, 109, 21))
            .padding(top = 70.dp), verticalArrangement = Arrangement.SpaceAround
    ) {
        items(results) { match ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp).padding(top = 20.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = match.strEvent, textAlign = TextAlign.Center, fontSize =15.sp, fontWeight = FontWeight.Bold )
                    Text(text = "${match.intHomeScore} : ${match.intAwayScore}",  textAlign = TextAlign.Center)
                    Text(text = match.dateEvent, style = MaterialTheme.typography.bodyMedium,  textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun TabletMatchList(results: List<Match>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(vertical = 40.dp), verticalArrangement = Arrangement.SpaceAround
    ) {
        items(results) { match ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = match.strEvent, textAlign = TextAlign.Center, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "${match.intHomeScore} : ${match.intAwayScore}", textAlign = TextAlign.Center)
                    Text(text = match.dateEvent, style = MaterialTheme.typography.bodyMedium, textAlign = TextAlign.Center)
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LeagueListScreen(navController: NavController, shareApp: (String) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val leaguesState = viewModel.getAllLeagues().observeAsState(initial = emptyList())
    val leagues: List<League>? = leaguesState.value

    if (leagues.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Please try to reconnect")
        }
    } else {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Leagues") }) }
        ) {
            if (isTablet()) {
                TabletLeagueList(leagues) { league ->
                    navController.navigate("seasonList/${league.idLeague}")
                }
            } else {
                LeagueList(leagues) { league ->
                    navController.navigate("seasonList/${league.idLeague}")
                }
            }
        }
    }
    IconButton(onClick = { shareApp("Check out these leagues!") }) {
        Icon(Icons.Default.Share, contentDescription = null)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeasonListScreen(leagueId: String?, navController: NavController, shareApp: (String) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val seasonsState = viewModel.getAllSeasons(leagueId ?: "").observeAsState(initial = emptyList())
    val seasons: List<Season>? = seasonsState.value

    if (seasons.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Please try to reconnect")
        }
    } else {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Seasons") }) }
        ) {
            if (isTablet()) {
                TabletSeasonList(seasons) { season ->
                    navController.navigate("tableList/${leagueId}/${season.strSeason}")
                }
            } else {
                SeasonList(seasons) { season ->
                    navController.navigate("tableList/${leagueId}/${season.strSeason}")
                }
            }
        }
    }
    IconButton(onClick = { shareApp("Check out these seasons!") }) {
        Icon(Icons.Default.Share, contentDescription = null)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TableListScreen(leagueId: String?, season: String?, navController: NavController, shareApp: (String) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val tableState = viewModel.getLeagueTable(leagueId ?: "", season ?: "").observeAsState(initial = emptyList())
    val table: List<TableEntry>? = tableState.value

    if (table.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Please try to reconnect")
        }
    } else {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Table") }) }
        ) {
            if (isTablet()) {
                TabletTableList(table) { entry ->
                    navController.navigate("matchList/${entry.idTeam}")
                }
            } else {
                TableList(table) { entry ->
                    navController.navigate("matchList/${entry.idTeam}")
                }
            }
        }
    }
    IconButton(onClick = { shareApp("Check out this table!") }) {
        Icon(Icons.Default.Share, contentDescription = null)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MatchListScreen(teamId: String?, shareApp: (String) -> Unit) {
    val viewModel: MainViewModel = viewModel()
    val matchesState = viewModel.getMatches(teamId ?: "").observeAsState(initial = emptyList())
    val results: List<Match>? = matchesState.value

    if (results.isNullOrEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Please try to reconnect")
        }
    } else {
        Scaffold(
            topBar = { TopAppBar(title = { Text("Matches") }) }
        ) {
            if (isTablet()) {
                TabletMatchList(results)
            } else {
                MatchList(results)
            }
        }
    }
    IconButton(onClick = { shareApp("Check out these matches!") }) {
        Icon(Icons.Default.Share, contentDescription = null)
    }
}


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

