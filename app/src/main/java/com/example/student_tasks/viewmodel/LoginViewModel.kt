package com.example.student_tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val repo = LoginRepository()

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = repo.LoginUser(loginRequest = loginRequest)
            print(response?.code())
            print("fsefweafewaefwaefrgaergawefweFWEFEFESGARGRGFGDA")
        }
    }
}