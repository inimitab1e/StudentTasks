package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.StringConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginInterface,
    private val prefHelper: PrefHelper
) : ViewModel() {
    private var _responseState = MutableLiveData<String>()
    val responseState get() = _responseState

//    private var _userExists = MutableLiveData<Boolean>()
//    val userExists get() = _userExists
//
//    private var _isTokenValid = MutableLiveData<Boolean>()
//    val isTokenValid get() = _isTokenValid
//
//    private var _isRefreshSuccess = MutableLiveData<Boolean>()
//    val isRefreshSuccess get() = _isRefreshSuccess

    fun loginUser(email: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(
                email = email,
                password = password
            )
            val response = loginRepo.loginUser(loginRequest = loginRequest)
            if (response?.errorBody() == null) {
                prefHelper.clear()
                prefHelper.saveUserInfo(
                    response?.body()?.accessToken,
                    response?.body()?.refreshToken,
                    email
                )
                _responseState.value = StringConstants.onSuccessLoggedIn
            } else {
                _responseState.value = response.message()
            }
        }
    }

//    fun checkIfUserValid() {
//        viewModelScope.launch {
//            if (prefHelper.getUserEmail() == null) {
//                _userExists.value = false
//            } else if (isAccessTokenValid()) {
//                _isTokenValid.value = true
//            } else {
//                refreshTokens()
//            }
//        }
//    }
//
//    private suspend fun refreshTokens() {
//        val email = prefHelper.getUserEmail().toString()
//        val refreshRequest = RefreshRequest(
//            email = email
//        )
//        val response = loginRepo.refreshTokens(
//            token = prefHelper.getRefreshToken().toString(),
//            refreshRequest = refreshRequest
//        )
//        if (response?.errorBody() == null) {
//            prefHelper.clear()
//            prefHelper.saveUserInfo(
//                response?.body()?.accessToken,
//                response?.body()?.refreshToken,
//                email
//            )
//            _isRefreshSuccess.value = true
//        } else {
//            _isRefreshSuccess.value = false
//        }
//    }
//
//    private suspend fun isAccessTokenValid(): Boolean {
//        val response = loginRepo.checkTokenValidity("Bearer " + prefHelper.getAccessToken().toString())
//        return response?.errorBody() == null
//    }
}