package com.cipta.projectautopost.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cipta.projectautopost.main.MainScreen
import com.cipta.projectautopost.post.PostScreen
import com.cipta.projectautopost.main.ScheduleScreen
import com.cipta.projectautopost.main.SettingScreen

@Composable
fun AppNavigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier // ← tambah modifier di sini
    ) {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.Post.route) { PostScreen(navController) }
        composable(Screen.Schedule.route) { ScheduleScreen(navController) }
        composable(Screen.Setting.route) { SettingScreen(navController) }
    }
}

