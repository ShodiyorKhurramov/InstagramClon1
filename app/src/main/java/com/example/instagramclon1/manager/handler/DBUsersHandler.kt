package com.example.instagramclon1.manager.handler


import com.example.instagramclon1.model.User
import java.lang.Exception

interface DBUsersHandler {
    fun onSuccess(users: ArrayList<User>)
    fun onError(e: Exception)
}