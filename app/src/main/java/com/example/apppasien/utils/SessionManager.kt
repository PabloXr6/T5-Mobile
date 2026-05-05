package com.example.apppasien.utils

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)

    fun saveAuthToken(token: String) {
        prefs.edit().putString("USER_TOKEN", token).apply()
    }

    fun getAuthToken(): String? {
        return prefs.getString("USER_TOKEN", null)
    }

    fun saveUserName(name: String) {
        prefs.edit().putString("USER_NAME", name).apply()
    }

    fun getUserName(): String? {
        return prefs.getString("USER_NAME", "User")
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}