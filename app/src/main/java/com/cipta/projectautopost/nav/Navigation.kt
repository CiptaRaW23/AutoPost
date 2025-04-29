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
import com.cipta.projectautopost.viewmodel.ScheduledPostViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: ScheduledPostViewModel,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Main.route,
        modifier = modifier // ← tambah modifier di sini
    ) {
        composable(Screen.Main.route) { MainScreen(navController) }
        composable(Screen.Post.route) {
            PostScreen(navController = navController, viewModel = viewModel)
        }

        composable(Screen.Schedule.route) {
            // Pastikan ScheduleScreen mendapatkan viewModel yang sama
            ScheduleScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.Setting.route) { SettingScreen(navController) }
    }
}

