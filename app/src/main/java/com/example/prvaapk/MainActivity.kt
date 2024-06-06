package com.example.prvaapk

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prvaapk.presentation.MatchListScreen
import com.example.prvaapk.presentation.TeamListScreen
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
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Football Teams") },
                            actions = {
                                IconButton(onClick = { /* TODO: Implement share functionality */ }) {
                                    Icon(Icons.Default.Share, contentDescription = null)
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
