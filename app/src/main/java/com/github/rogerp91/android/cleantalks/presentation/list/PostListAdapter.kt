package com.github.rogerp91.android.cleantalks.presentation.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.rogerp91.android.cleantalks.R
import com.github.rogerp91.android.cleantalks.presentation.common.model.PostItem
import com.github.rogerp91.android.cleantalks.presentation.common.util.inflate
import kotlinx.android.synthetic.main.include_user_info.view.*
import kotlinx.android.synthetic.main.item_list_post.view.*

/**
 * Created by rpatino on oct/2019
 */
class PostListAdapter constructor(
    private val itemClick: (PostItem) -> Unit
) : ListAdapter<PostItem, PostListAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_post)) {

        fun bind(item: PostItem) {
            //itemView.userAvatar.loadAvatar(item.email)
            itemView.userUsername.text = "@${item.username}"
            itemView.userName.text = item.name
            itemView.postTitle.text = item.title.capitalize()
            itemView.postBody.text = item.body.capitalize()
            itemView.setOnClickListener { itemClick.invoke(item) }
        }
    }
}

private class PostDiffCallback : DiffUtil.ItemCallback<PostItem>() {
    override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
        oldItem.postId == newItem.postId

    override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean =
        oldItem == newItem
}