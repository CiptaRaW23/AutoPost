package com.cipta.projectautopost.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduledPostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduledPost(post: ScheduledPost)

    @Query("SELECT * FROM scheduled_posts ORDER BY scheduledTimeMillis ASC")
    fun getAllScheduledPosts(): Flow<List<ScheduledPost>>

    @Delete
    suspend fun deleteScheduledPost(post: ScheduledPost)

    @Update
    suspend fun updateScheduledPost(post: ScheduledPost)
}

