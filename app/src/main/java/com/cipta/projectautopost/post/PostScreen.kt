package com.cipta.projectautopost.post

import android.net.Uri
import android.widget.MediaController
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.cipta.projectautopost.data.ScheduledPost
import com.cipta.projectautopost.viewmodel.ScheduledPostViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView
import java.util.Calendar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(
    navController: NavController,
    viewModel: ScheduledPostViewModel
) {
    var caption by remember { mutableStateOf("") }
    var videoUrl by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedVideoUri by remember { mutableStateOf<Uri?>(null) }
    var showDateTimePicker by remember { mutableStateOf(false) }
    var scheduledTime by remember { mutableStateOf<Calendar?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var scheduledTimeMillis by remember { mutableStateOf<Long?>(null) }

    val context = LocalContext.current

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                selectedImageUri = uri
                selectedVideoUri = null
            }
        }
    )

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                selectedVideoUri = uri
                selectedImageUri = null
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buat Postingan") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = caption,
                onValueChange = { caption = it },
                label = { Text("Caption Postingan") },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 3
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = videoUrl,
                onValueChange = { videoUrl = it },
                label = { Text("URL Video (Opsional)") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { imagePickerLauncher.launch("image/*") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.Image, contentDescription = "Pilih Foto")
                    Spacer(Modifier.width(8.dp))
                    Text("Pilih Foto")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { videoPickerLauncher.launch("video/*") },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(Icons.Default.VideoLibrary, contentDescription = "Pilih Video")
                    Spacer(Modifier.width(8.dp))
                    Text("Pilih Video")
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(8.dp)
            ) {
                when {
                    isLoading -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }
                    selectedImageUri != null -> {
                        ImagePreview(selectedImageUri!!)
                    }
                    selectedVideoUri != null -> {
                        VideoPlayer(uri = selectedVideoUri!!)
                    }
                    else -> {
                        Text(
                            "Tidak ada media yang dipilih",
                            modifier = Modifier.align(Alignment.Center),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { showDateTimePicker = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.Schedule, contentDescription = "Atur Jadwal")
                Spacer(Modifier.width(8.dp))
                Text("Atur Jadwal")
            }

            // Output untuk tanggal dan waktu jadwal dipindahkan ke bawah tombol Atur Jadwal
            scheduledTime?.let { calendar ->
                Text(
                    text = "Posting dijadwalkan pada: ${calendar.time}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val scheduledTimeMillis = scheduledTime?.timeInMillis ?: System.currentTimeMillis()

                    val mediaType = when {
                        selectedImageUri != null -> "image"
                        selectedVideoUri != null -> "video"
                        videoUrl.isNotBlank() -> "video"
                        else -> "text"
                    }

                    val mediaUri = selectedImageUri?.toString()
                        ?: selectedVideoUri?.toString()
                        ?: videoUrl.takeIf { it.isNotBlank() }

                    // Simpan ke database
                    viewModel.addScheduledPost(
                        ScheduledPost(
                            caption = caption,
                            mediaUri = mediaUri,
                            scheduledTimeMillis = scheduledTimeMillis,
                            mediaType = mediaType,
                            uploaded = false
                        )
                    )

                    // Balik ke halaman sebelumnya
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = caption.isNotBlank() || videoUrl.isNotBlank() ||
                        selectedImageUri != null || selectedVideoUri != null
            ) {
                Text("Upload Postingan")
            }
        }
    }

    if (showDateTimePicker) {
        DateTimePickerDialog(
            onDateTimeSelected = { selectedCalendar ->
                scheduledTime = selectedCalendar
                showDateTimePicker = false
            },
            onDismiss = {
                showDateTimePicker = false
            }
        )
    }
}

@Composable
fun ImagePreview(uri: Uri) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(uri)
            .crossfade(true)
            .build(),
        contentDescription = "Gambar yang dipilih",
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun VideoPlayer(uri: Uri) {
    val context = LocalContext.current
    val player = remember { ExoPlayer.Builder(context).build() }

    LaunchedEffect(uri) {
        player.setMediaItem(MediaItem.fromUri(uri))
        player.prepare()
        player.play()
    }

    AndroidView(
        factory = { context ->
            StyledPlayerView(context).apply {
                this.player = player
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
