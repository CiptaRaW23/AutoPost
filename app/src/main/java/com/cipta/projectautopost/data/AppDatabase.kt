package com.cipta.projectautopost.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ScheduledPost::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun scheduledPostDao(): ScheduledPostDao
}