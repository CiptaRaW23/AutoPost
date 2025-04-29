package com.cipta.projectautopost.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scheduled_posts")
data class ScheduledPost(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caption: String,
    val mediaUri: String?,  // Bisa gambar/video, nullable
    val scheduledTimeMillis: Long, // Waktu jadwal posting dalam millisecond
    val mediaType: String, // "image", "video", atau "text"
    val uploaded: Boolean = false // Untuk menandai apakah sudah diupload ke Facebook
)
