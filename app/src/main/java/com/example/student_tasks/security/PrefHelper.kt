package com.example.student_tasks.security

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PrefHelper(context: Context) {

    private val PREFS_NAME = "Secret"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun saveUserInfo(token: String?, email: String) {
        editor.putString("AccessToken", token)
            .putString("email", email)
            .apply()
    }

    fun getAccessToken(): String? {
        return sharedPref.getString("AccessToken", null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }
}