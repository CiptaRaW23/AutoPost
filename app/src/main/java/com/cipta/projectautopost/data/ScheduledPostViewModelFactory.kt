package com.cipta.projectautopost.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cipta.projectautopost.viewmodel.ScheduledPostViewModel

class ScheduledPostViewModelFactory(
    private val repository: ScheduledPostRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScheduledPostViewModel::class.java)) {
            return ScheduledPostViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
