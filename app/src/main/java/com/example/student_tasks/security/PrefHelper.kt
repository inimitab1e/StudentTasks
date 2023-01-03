package com.example.student_tasks.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PrefHelper(context: Context) {

    private val PREFS_NAME = "Secret"
    private var sharedPref: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun saveUserInfo(accessToken: String?, refreshToken: String?, email: String) {
        editor.putString("AccessToken", accessToken)
            .putString("RefreshToken", refreshToken)
            .putString("Email", email)
            .apply()
    }

    fun deleteKey(key: String) {
        editor.remove(key)
            .apply()
     }

    fun getAccessToken(): String? = sharedPref.getString("AccessToken", null)

    fun getRefreshToken(): String? = sharedPref.getString("RefreshToken", null)

    fun getUserEmail(): String? = sharedPref.getString("Email", null)

    fun clear() {
        editor.clear()
            .apply()
    }
}