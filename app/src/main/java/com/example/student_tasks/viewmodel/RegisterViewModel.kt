package com.example.student_tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.RegisterRequest
import com.example.student_tasks.interfaces.LoginResultCallBacks
import com.example.student_tasks.repository.RegisterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepo: RegisterRepository,

): ViewModel() {
    var authListener: LoginResultCallBacks ?= null

    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            val registerRequest = RegisterRequest(
                userName = username,
                email = email,
                password = password
            )
            val response = registerRepo.RegisterUser(registerRequest = registerRequest)

            if (response?.raw()?.code == 200) {
                authListener?.onSuccess("You have successfully registered!")
            } else {
                authListener?.onError("Error")
            }
        }
    }
}