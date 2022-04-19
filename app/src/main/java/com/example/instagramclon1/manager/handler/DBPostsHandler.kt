package com.example.instagramclon1.manager.handler


import com.example.instagramclon1.model.Post
import java.lang.Exception

interface DBPostsHandler {
    fun onSuccess(posts: ArrayList<Post>)
    fun onError(e: Exception)
}