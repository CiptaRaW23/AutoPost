package com.cipta.projectautopost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.cipta.projectautopost.data.AppDatabase
import com.cipta.projectautopost.data.ScheduledPostRepository
import com.cipta.projectautopost.data.ScheduledPostViewModelFactory
import com.cipta.projectautopost.nav.AppNavigation
import com.cipta.projectautopost.ui.theme.ProjectAutoPostTheme
import com.cipta.projectautopost.viewmodel.ScheduledPostViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inisialisasi database dan repository
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "scheduled-posts-db"
        ).build()

        val repository = ScheduledPostRepository(db.scheduledPostDao())

        setContent {
            val navController = rememberNavController()
            val viewModel: ScheduledPostViewModel = viewModel(
                factory = ScheduledPostViewModelFactory(repository)
            )

            ProjectAutoPostTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        navController = navController,
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
