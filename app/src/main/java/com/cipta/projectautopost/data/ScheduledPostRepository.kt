package com.cipta.projectautopost.data

import kotlinx.coroutines.flow.Flow

class ScheduledPostRepository(private val dao: ScheduledPostDao) {

    fun getAllScheduledPosts(): Flow<List<ScheduledPost>> = dao.getAllScheduledPosts()

    suspend fun insertScheduledPost(post: ScheduledPost) = dao.insertScheduledPost(post)

    suspend fun deleteScheduledPost(post: ScheduledPost) = dao.deleteScheduledPost(post)

    suspend fun updateScheduledPost(post: ScheduledPost) = dao.updateScheduledPost(post)
}
