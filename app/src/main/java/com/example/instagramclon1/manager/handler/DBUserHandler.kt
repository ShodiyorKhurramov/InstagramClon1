package com.example.instagramclon1.manager.handler


import com.example.instagramclon1.model.User
import java.lang.Exception

interface DBUserHandler {
    fun onSuccess(user: User? = null)
    fun onError(e: Exception)
}