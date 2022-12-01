package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.room.Users
import com.example.student_tasks.repository.StudentListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentListViewModel @Inject constructor(
    private val repo: StudentListRepository
): ViewModel() {
    private var _userList = MutableLiveData<List<Users>>()
    val userList get() = _userList

    fun updateList() {
        viewModelScope.launch {
            val response = repo.getRemoteUsersList()
            val listOfUsers = response?.body()?.usersResponseList
            repo.updateLocalList(listOfUsers, listOfUsers?.size)
            _userList.value = repo.getLocalList()
        }
    }
}