package com.github.rogerp91.android.cleantalks.presentation.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.github.rogerp91.android.cleantalks.R
import com.github.rogerp91.android.cleantalks.presentation.common.base.BaseActivity
import com.github.rogerp91.android.cleantalks.presentation.common.model.PostItem
import com.github.rogerp91.android.cleantalks.presentation.common.router.Routing
import com.github.rogerp91.android.cleantalks.presentation.common.util.Resource
import com.github.rogerp91.android.cleantalks.presentation.common.util.ResourceState
import com.github.rogerp91.android.cleantalks.presentation.common.util.startRefreshing
import com.github.rogerp91.android.cleantalks.presentation.common.util.stopRefreshing
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.viewModel

class PostListActivity : BaseActivity() {

    private val vm: PostListViewModel by viewModel()

    private val snackBar by lazy {
        Snackbar.make(swipeRefreshLayout, getString(R.string.error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) { vm.get(refresh = true) }
    }
    private val itemClick: (PostItem) -> Unit =
        { startActivity(Routing.postDetails(userId = it.userId, postId = it.postId)) }

    private val adapter = PostListAdapter(itemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            vm.get(refresh = false)
        }

        postsRecyclerView.adapter = adapter

        vm.posts.observe(this, Observer { updatePosts(it) })
        swipeRefreshLayout.setOnRefreshListener { vm.get(refresh = true) }
    }

    private fun updatePosts(resource: Resource<List<PostItem>>?) {
        resource?.let {
            when (it.state) {
                ResourceState.LOADING -> swipeRefreshLayout.startRefreshing()
                ResourceState.SUCCESS -> swipeRefreshLayout.stopRefreshing()
                ResourceState.ERROR -> swipeRefreshLayout.stopRefreshing()
            }
            it.data?.let { adapter.submitList(it) }
            it.message?.let { snackBar.show() }
        }

    }
}