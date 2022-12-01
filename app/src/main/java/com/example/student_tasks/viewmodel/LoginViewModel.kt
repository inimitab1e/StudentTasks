package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.repository.LoginRepository
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.StringConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginRepository,
    private val prefHelper: PrefHelper,
): ViewModel() {
    private var _state = MutableLiveData<String>()
    val state get() = _state

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = loginRepo.LoginUser(loginRequest = loginRequest)
            if (response?.errorBody() == null) {
                prefHelper.clear()
                prefHelper.saveUserInfo(response?.body()?.accessToken, email)
                _state.value = StringConstants.onSuccessLoggedIn
            } else {
                _state.value = response.message()
            }
        }
    }
}