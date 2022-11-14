package com.example.student_tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.RegisterRequest
import com.example.student_tasks.interfaces.LoginResultCallBacks
import com.example.student_tasks.repository.RegisterRepository
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {

    private val repo = RegisterRepository()
    var authListener: LoginResultCallBacks ?= null

    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            val registerRequest = RegisterRequest(
                userName = username,
                email = email,
                password = password
            )
            val response = repo.RegisterUser(registerRequest = registerRequest)

            if (response?.raw()?.code == 200) {
                authListener?.onSuccess("You have successfully registered!")
            } else {
                authListener?.onError("Error")
            }
        }
    }
}