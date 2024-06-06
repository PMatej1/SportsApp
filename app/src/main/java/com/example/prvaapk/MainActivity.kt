package com.example.prvaapk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prvaapk.presentation.MatchListScreen
import com.example.prvaapk.presentation.TeamListScreen
import com.example.prvaapk.ui.theme.PrvaApkTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrvaApkTheme {
                val navController = rememberNavController()
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Football Teams") },
                            actions = {
                                IconButton(onClick = { /* TODO: Implement share functionality */ }) {
                                    Icon(Icons.Filled.Share, contentDescription = null)
                                }
                            }
                        )
                    }
                ) {
                    NavHost(navController, startDestination = "teamList") {
                        composable("teamList") { TeamListScreen(navController) }
                        composable("matchList/{teamId}") { backStackEntry ->
                            MatchListScreen(backStackEntry.arguments?.getString("teamId"))
                        }
                    }
                }
            }
        }
    }
}
