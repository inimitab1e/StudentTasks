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

    fun saveAccessToken(value: String?) {
        editor.putString("AccessToken", value)
            .apply()
    }

    fun getAccessToken(): String? {
        return sharedPref.getString("AccessToken", null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

//    private val sharedPrefsFile: String = "Secret"
//    private val mainKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
//
//    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
//        sharedPrefsFile,
//        mainKeyAlias,
//        context,
//        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
//        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
//    )
//
//    fun saveAccessToken(token: String?) {
//        with (sharedPreferences.edit()) {
//            putString(token, "Access token")
//            apply()
//        }
//    }
//
//    fun getSecretInfo() = sharedPreferences.getString("Access token", null)
}