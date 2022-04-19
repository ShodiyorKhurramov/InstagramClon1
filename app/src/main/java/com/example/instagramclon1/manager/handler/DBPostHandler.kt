package com.example.instagramclon1.manager.handler


import com.example.instagramclon1.model.Post
import java.lang.Exception

interface DBPostHandler {
    fun onSuccess(post: Post)
    fun onError(e: Exception)
}