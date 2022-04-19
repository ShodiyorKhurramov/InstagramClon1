package com.example.instagramclon1.manager.handler


import java.lang.Exception

interface DBFollowHandler {
    fun onSuccess(isFollowed: Boolean)
    fun onError(e: Exception)
}