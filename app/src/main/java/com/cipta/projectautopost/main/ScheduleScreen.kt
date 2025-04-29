package com.cipta.projectautopost.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState  // Pastikan import ini ada
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.cipta.projectautopost.data.ScheduledPost
import com.cipta.projectautopost.viewmodel.ScheduledPostViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ScheduleScreen(navController: NavHostController, viewModel: ScheduledPostViewModel) {
    // Menggunakan collectAsState() untuk mengamati StateFlow secara reaktif
    val scheduledPosts = viewModel.scheduledPosts.collectAsState(initial = emptyList()).value

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text(
            text = "Daftar Jadwal Posting",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(scheduledPosts) { post ->
                ScheduledPostItem(post)
            }
        }
    }
}

@Composable
fun ScheduledPostItem(post: ScheduledPost) {
    val formatter = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
    val dateString = formatter.format(Date(post.scheduledTimeMillis))

    Card(
        elevation = CardDefaults.cardElevation(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
        ) {
            Text(
                text = "Caption: ${post.caption}",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Dijadwalkan: $dateString",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Status: ${if (post.uploaded) "Sudah Dipost" else "Belum Dipost"}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}
