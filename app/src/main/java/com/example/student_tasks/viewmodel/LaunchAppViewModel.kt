package com.example.student_tasks.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.security.PrefHelper
import com.example.student_tasks.utils.StringConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchAppViewModel @Inject constructor(
    private val launchAppRepo: LaunchAppInterface,
    private val prefHelper: PrefHelper
) : ViewModel() {

    private var _userExists = MutableLiveData<String>()
    val userExists get() = _userExists

    private var _isTokenValid = MutableLiveData<String>()
    val isTokenValid get() = _isTokenValid

    private var _isRefreshSuccess = MutableLiveData<String>()
    val isRefreshSuccess get() = _isRefreshSuccess

    fun checkIfUserValid() {
        viewModelScope.launch {
            if (prefHelper.getUserEmail() == null) {
                _userExists.postValue(StringConstants.userNotExists)
            } else if (isAccessTokenValid()) {
                _isTokenValid.postValue(StringConstants.tokenValid)
            } else {
                refreshTokens()
            }
        }
    }

    private fun refreshTokens() {
        viewModelScope.launch {
            val email = prefHelper.getUserEmail().toString()
            val refreshRequest = RefreshRequest(
                email = email
            )
            val response = launchAppRepo.refreshTokens(
                token = prefHelper.getRefreshToken().toString(),
                refreshRequest = refreshRequest
            )
            if (response?.errorBody() == null) {
                prefHelper.clear()
                prefHelper.saveUserInfo(
                    response?.body()?.accessToken,
                    response?.body()?.refreshToken,
                    email
                )
                _isRefreshSuccess.postValue(StringConstants.refreshSuccess)
            } else {
                _isRefreshSuccess.postValue(StringConstants.refreshFailed)
            }
        }
    }

    private suspend fun isAccessTokenValid(): Boolean {
        val response = launchAppRepo.checkTokenValidity("Bearer " + prefHelper.getAccessToken().toString())
        return (response?.errorBody() == null)
    }
}