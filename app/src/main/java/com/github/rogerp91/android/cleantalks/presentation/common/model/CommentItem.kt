package com.github.rogerp91.android.cleantalks.presentation.common.model

import com.github.rogerp91.android.cleantalks.domain.model.Comment

/**
 * Created by rpatino on oct/2019
 */
data class CommentItem(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)

fun List<Comment>.mapToPresentation(): List<CommentItem> =
    map { CommentItem(it.postId, it.id, it.name, it.email, it.body) }