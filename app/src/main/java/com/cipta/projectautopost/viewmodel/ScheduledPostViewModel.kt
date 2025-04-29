package com.cipta.projectautopost.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipta.projectautopost.data.ScheduledPost
import com.cipta.projectautopost.data.ScheduledPostRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ScheduledPostViewModel(private val repository: ScheduledPostRepository) : ViewModel() {

    val scheduledPosts: StateFlow<List<ScheduledPost>> = repository.getAllScheduledPosts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addScheduledPost(post: ScheduledPost) {
        viewModelScope.launch {
            repository.insertScheduledPost(post)
        }
    }

    fun deleteScheduledPost(post: ScheduledPost) {
        viewModelScope.launch {
            repository.deleteScheduledPost(post)
        }
    }
}
