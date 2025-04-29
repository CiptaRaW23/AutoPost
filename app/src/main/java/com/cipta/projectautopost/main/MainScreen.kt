package com.cipta.projectautopost.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.cipta.projectautopost.nav.Screen

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize() // Pastikan kolom mengisi seluruh layar
            .background(brush = androidx.compose.ui.graphics.Brush.verticalGradient(listOf(Color(0xFF4B169D), Color(0xFF763ECD)))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Tombol Buat Postingan
        Button(
            onClick = { navController.navigate(Screen.Post.route) },
            modifier = Modifier
                .fillMaxWidth(0.8f) // Mengatur lebar tombol menjadi 80% dari lebar layar
                .padding(vertical = 8.dp) // Memberi jarak vertikal
                .height(56.dp), // Atur tinggi tombol
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF763ECD)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Post", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Buat Postingan", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Lihat Jadwal
        Button(
            onClick = { navController.navigate(Screen.Schedule.route) },
            modifier = Modifier
                .fillMaxWidth(0.8f) // Mengatur lebar tombol menjadi 80% dari lebar layar
                .padding(vertical = 8.dp) // Memberi jarak vertikal
                .height(56.dp), // Atur tinggi tombol
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFBEB6F7)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Schedule, contentDescription = "View Schedule", tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Lihat Jadwal", color = Color.Black, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Pengaturan Token/PageID
        Button(
            onClick = { navController.navigate(Screen.Setting.route) },
            modifier = Modifier
                .fillMaxWidth(0.8f) // Mengatur lebar tombol menjadi 80% dari lebar layar
                .padding(vertical = 8.dp) // Memberi jarak vertikal
                .height(56.dp), // Atur tinggi tombol
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4B169D)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Icon(Icons.Filled.Settings, contentDescription = "Settings", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Pengaturan Token/PageID", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    val navController = rememberNavController()  // Gunakan rememberNavController() untuk Preview
    MainScreen(navController = navController)
}
