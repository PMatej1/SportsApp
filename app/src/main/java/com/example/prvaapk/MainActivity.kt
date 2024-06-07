package com.example.prvaapk

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prvaapk.presentation.LeagueListScreen
import com.example.prvaapk.presentation.SeasonListScreen
import com.example.prvaapk.presentation.TableListScreen
import com.example.prvaapk.presentation.MatchListScreen
import com.example.prvaapk.ui.theme.PrvaApkTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter",
        "UnusedMaterial3ScaffoldPaddingParameter"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrvaApkTheme {
                val navController = rememberNavController()
                var title by remember {
                    mutableStateOf("Leagues")
                }
                Scaffold(
                    modifier = Modifier
                        .background(color = Color.Green)
                        .fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarColors(containerColor = Color(19,90,21), Color(19,90,21),Color(19,90,21), Color.White, Color.White),
                            title = { Text(title) },
                            actions = {
                                IconButton(onClick = { shareApp("Check out this awesome Football Leagues app!")}) {
                                    Icon(Icons.Default.Share, contentDescription = null)
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController, startDestination = "leagueList") {
                        composable("leagueList") { LeagueListScreen(navController, ::shareApp) }
                        composable("seasonList/{leagueId}") { backStackEntry ->
                            title="Seasons"
                            SeasonListScreen(backStackEntry.arguments?.getString("leagueId"), navController, ::shareApp)
                        }
                        composable("tableList/{leagueId}/{season}") { backStackEntry ->
                            title="Standings"
                            TableListScreen(backStackEntry.arguments?.getString("leagueId"), backStackEntry.arguments?.getString("season"), navController, ::shareApp)
                        }
                        composable("matchList/{teamId}") { backStackEntry ->
                            title="Last 5 games"
                            MatchListScreen(backStackEntry.arguments?.getString("teamId"), ::shareApp)
                        }
                    }
                }
            }

        }
    }
    private fun shareApp(message: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}

