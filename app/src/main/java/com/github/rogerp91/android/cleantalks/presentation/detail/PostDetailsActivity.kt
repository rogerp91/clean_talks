package com.github.rogerp91.android.cleantalks.presentation.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.github.rogerp91.android.cleantalks.R
import com.github.rogerp91.android.cleantalks.presentation.common.base.BaseActivity
import com.github.rogerp91.android.cleantalks.presentation.common.model.CommentItem
import com.github.rogerp91.android.cleantalks.presentation.common.model.PostItem
import com.github.rogerp91.android.cleantalks.presentation.common.router.Routing
import com.github.rogerp91.android.cleantalks.presentation.common.util.Resource
import com.github.rogerp91.android.cleantalks.presentation.common.util.ResourceState
import com.github.rogerp91.android.cleantalks.presentation.common.util.gone
import com.github.rogerp91.android.cleantalks.presentation.common.util.loadImage
import com.github.rogerp91.android.cleantalks.presentation.common.util.visible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_post_details.*
import kotlinx.android.synthetic.main.include_user_info.*
import kotlinx.android.synthetic.main.item_list_post.*
import org.koin.androidx.viewmodel.ext.viewModel

/**
 * Created by rpatino on oct/2019
 */
class PostDetailsActivity : BaseActivity() {

    private val vm: PostDetailsViewModel by viewModel()
    private val adapter = CommentsAdapter()
    private val userId by lazy { intent.getStringExtra(Routing.USER_ID_KEY) }
    private val postId by lazy { intent.getStringExtra(Routing.POST_ID_KEY) }
    private val snackBar by lazy {
        Snackbar.make(container, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.getComments(postId, refresh = true) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_details)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        commentsRecyclerView.isNestedScrollingEnabled = false
        commentsRecyclerView.adapter = adapter

        if (savedInstanceState == null) {
            vm.getPost(UserIdPostId(userId, postId))
            vm.getComments(postId, refresh = false)
        }

        vm.post.observe(this, Observer { updatePost(it) })
        vm.comments.observe(this, Observer { updateComments(it) })
    }

    private fun updatePost(postItem: PostItem?) {
        postItem?.let {
            userAvatar.loadImage(it.email)
            userUsername.text = "@${it.username}"
            userName.text = it.name
            postTitle.text = it.title.capitalize()
            postBody.maxLines = Int.MAX_VALUE
            postBody.text = it.body.capitalize()
        }
    }

    private fun updateComments(resource: Resource<List<CommentItem>>?) {
        resource?.let { it ->
            when (it.state) {
                ResourceState.LOADING -> progressBar.visible()
                ResourceState.SUCCESS -> progressBar.gone()
                ResourceState.ERROR -> progressBar.gone()
            }
            it.data?.let { adapter.submitList(it) }
            it.message?.let { snackBar.show() }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}