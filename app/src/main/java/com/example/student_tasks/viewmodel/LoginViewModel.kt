package com.example.student_tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.repository.LoginRepository
import com.example.student_tasks.security.PrefHelper
import kotlinx.coroutines.launch

class LoginViewModel(app: Application): AndroidViewModel(app) {

    private val repo = LoginRepository()

    private val prefHelper: PrefHelper by lazy {
        PrefHelper(getApplication())
    }

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = repo.LoginUser(loginRequest = loginRequest)
            prefHelper.saveAccessToken(response?.body()?.accessToken)
            val test = prefHelper.getAccessToken()
        }
    }
}