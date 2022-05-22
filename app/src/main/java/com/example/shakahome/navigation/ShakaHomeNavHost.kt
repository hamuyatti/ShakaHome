package com.example.shakahome.navigation

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.ui.navigation.ReportDestination


@Composable
fun ShakaHomeNavHost(
    windowSizeClass : WindowSizeClass,
    modifier: Modifier = Modifier,
    navController : NavHostController = rememberNavController(),
    startDestination : String = ReportDestination.route
){
    NavHost(navController = navController, startDestination = startDestination, modifier = modifier){

    }
}