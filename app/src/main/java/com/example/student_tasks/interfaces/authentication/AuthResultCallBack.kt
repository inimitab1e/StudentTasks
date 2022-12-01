package com.example.student_tasks.interfaces.authentication

interface AuthResultCallBack {
    fun onSuccess(message: String)
    fun onError(message: String)
}