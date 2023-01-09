package com.example.student_tasks.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.student_tasks.data.model.RefreshRequest
import com.example.student_tasks.interfaces.authentication.LaunchAppInterface
import com.example.student_tasks.network.exceptions.NetworkResponse
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
                _isTokenValid.postValue(StringConstants.tokenIsValid)
            } else {
                refreshTokens()
            }
        }
    }

    private suspend fun refreshTokens() {
        prefHelper.deleteKey(key = "AccessToken")
        val email = prefHelper.getUserEmail().toString()
        val refreshRequest = RefreshRequest(
            email = email
        )
        val response = launchAppRepo.refreshTokens(
            refreshRequest = refreshRequest
        )
        when (response) {
            is NetworkResponse.Success -> changeTokensValue(
                response.body.accessToken,
                response.body.refreshToken,
                email
            )
            else -> _isRefreshSuccess.postValue(StringConstants.refreshFailed)
        }
    }

    private suspend fun isAccessTokenValid(): Boolean {
        val response = launchAppRepo.checkTokenValidity()
        return when (response) {
            is NetworkResponse.Success -> true
            else -> false
        }
    }

    private fun changeTokensValue(
        accessToken: String,
        refreshToken: String,
        email: String
    ) {
        prefHelper.clear()
        prefHelper.saveUserInfo(
            accessToken,
            refreshToken,
            email
        )
        _isRefreshSuccess.postValue(StringConstants.refreshSuccess)
    }
}