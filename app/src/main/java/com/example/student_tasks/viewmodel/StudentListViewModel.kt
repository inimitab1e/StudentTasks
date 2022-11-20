package com.example.student_tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.repository.StudentListRepository
import kotlinx.coroutines.launch

class StudentListViewModel: ViewModel() {

    private val repo = StudentListRepository()

    fun updateList() {
        viewModelScope.launch {
            val response = repo.updateUsersList()
        }
    }
}