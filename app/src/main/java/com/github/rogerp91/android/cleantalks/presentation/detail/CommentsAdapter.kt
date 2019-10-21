package com.github.rogerp91.android.cleantalks.presentation.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.rogerp91.android.cleantalks.R
import com.github.rogerp91.android.cleantalks.presentation.common.model.CommentItem
import com.github.rogerp91.android.cleantalks.presentation.common.util.inflate
import com.github.rogerp91.android.cleantalks.presentation.common.util.loadImage
import kotlinx.android.synthetic.main.include_user_info.view.*
import kotlinx.android.synthetic.main.item_list_comment.view.*

/**
 * Created by rpatino on oct/2019
 */
class CommentsAdapter :
    ListAdapter<CommentItem, CommentsAdapter.ViewHolder>(CommentDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflate(R.layout.item_list_comment)) {

        fun bind(item: CommentItem) {
            itemView.userAvatar.loadImage(item.email)
            itemView.userName.text = item.name.capitalize()
            itemView.commentBody.text = item.body.capitalize()
        }
    }
}

private class CommentDiffCallback : DiffUtil.ItemCallback<CommentItem>() {
    override fun areItemsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: CommentItem, newItem: CommentItem): Boolean =
        oldItem == newItem
}