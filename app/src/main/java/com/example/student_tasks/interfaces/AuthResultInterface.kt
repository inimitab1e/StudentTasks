package com.example.student_tasks.interfaces

interface LoginResultCallBacks {
    fun onSuccess(message:String)
    fun onError(message: String)
}