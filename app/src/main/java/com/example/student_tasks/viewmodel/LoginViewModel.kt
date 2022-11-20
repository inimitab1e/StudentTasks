package com.example.student_tasks.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.student_tasks.MainActivity
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val repo = LoginRepository()

    val sharedPrefsFile: String = "Secret"
    val mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        sharedPrefsFile,
        mainKeyAlias,
        MainActivity.applicationContext(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )


    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = repo.LoginUser(loginRequest = loginRequest)

            with (sharedPreferences.edit()) {
                putString(response?.body()?.accessToken, "Access token")
                apply()
            }

        }
    }
}