package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.RegisterRequest
import com.example.student_tasks.interfaces.authentication.RegisterInterface
import com.example.student_tasks.repository.RegisterRepository
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.StringConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerRepo: RegisterInterface,
    private val prefHelper: PrefHelper
): ViewModel() {
    private var _state = MutableLiveData<String>()
    val state get() = _state

    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            val registerRequest = RegisterRequest(
                userName = username,
                email = email,
                password = password
            )
            val response = registerRepo.RegisterUser(registerRequest = registerRequest)
            if (response?.errorBody() == null) {
                prefHelper.clear()
                prefHelper.saveUserInfo(
                    response?.body()?.accessToken,
                    response?.body()?.refreshToken,
                    email = email
                )
                _state.value = StringConstants.onSuccessLoggedIn
            } else {
                _state.value = response.message()
            }
        }
    }
}