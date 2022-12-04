package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.LoginRequest
import com.example.student_tasks.interfaces.authentication.LoginInterface
import com.example.student_tasks.repository.LoginRepository
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.StringConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepo: LoginInterface,
    private val prefHelper: PrefHelper
): ViewModel() {
    private var _responseState = MutableLiveData<String>()
    val responseState get() = _responseState

    private var _userExists = MutableLiveData<Boolean>()
    val userExists get() = _userExists

    private var _isTokenValid = MutableLiveData<Boolean>()
    val isTokenValid get() = _isTokenValid

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
                _responseState.value = StringConstants.onSuccessLoggedIn
            } else {
                _responseState.value = response.message()
            }
        }
    }

//    fun checkIfUserValid() {
//        viewModelScope.launch {
//            if(prefHelper.getUserEmail() == null) {
//                _userExists.value = false
//            } else if (check validity == false) {
//                _isTokenValid.value = false
//            } else {
//            _userExists.value = true
//            _isTokenValid.value = true
//            }
//        }
//    }
}