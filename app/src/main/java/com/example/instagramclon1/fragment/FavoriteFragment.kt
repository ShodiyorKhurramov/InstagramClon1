package com.example.instagramclon1.fragment



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramclon1.R
import com.example.instagramclon1.adapter.FavoriteAdapter
import com.example.instagramclon1.manager.AuthManager
import com.example.instagramclon1.manager.DatabaseManager
import com.example.instagramclon1.manager.handler.DBPostHandler
import com.example.instagramclon1.manager.handler.DBPostsHandler
import com.example.instagramclon1.model.Post
import com.example.instagramclon1.utils.DialogListener
import com.example.instagramclon1.utils.Utils


class FavoriteFragment : BaseFragment() {
    val TAG = FavoriteFragment::class.java.simpleName
    lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)
        initViews(view)
        return view
    }

    private fun initViews(view: View){
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.setLayoutManager(GridLayoutManager(activity, 1))

        refreshAdapter(loadPosts())
        loadLikedFeeds()
    }



    private fun refreshAdapter(items: ArrayList<Post>) {
        val adapter = FavoriteAdapter(this, items)
        recyclerView.adapter = adapter
    }

    fun likeOrUnlikePost(post: Post) {
        val uid = AuthManager.currentUser()!!.uid
        DatabaseManager.likeFeedPost(uid, post)

        loadLikedFeeds()
    }

    fun loadLikedFeeds() {
        showLoading(requireActivity())
        val uid = AuthManager.currentUser()!!.uid
        DatabaseManager.loadLikedFeeds(uid, object : DBPostsHandler {
            override fun onSuccess(posts: ArrayList<Post>) {
                dismissLoading()
                refreshAdapter(posts)
            }

            override fun onError(e: Exception) {
                dismissLoading()
            }
        })
    }

    fun showDeleteDialog(post: Post){
        Utils.dialogDouble(requireContext(), getString(R.string.str_delete_post), object :
            DialogListener {
            override fun onCallback(isChosen: Boolean) {
                if(isChosen){
                    deletePost(post)
                }
            }
        })
    }

    fun deletePost(post: Post) {
        DatabaseManager.deletePost(post, object : DBPostHandler {
            override fun onSuccess(post: Post) {
                loadLikedFeeds()
            }

            override fun onError(e: Exception) {

            }
        })
    }

    private fun loadPosts():ArrayList<Post>{
        val items = ArrayList<Post>()
        items.add(Post("Here we go","https://images.unsplash.com/photo-1557053815-9f79f70c7980?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1052&q=80"))
        items.add(Post("Here we go","https://images.unsplash.com/photo-1529156069898-49953e39b3ac?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1932&q=80"))
        items.add(Post("Here we go","https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"))
        return items
    }
}