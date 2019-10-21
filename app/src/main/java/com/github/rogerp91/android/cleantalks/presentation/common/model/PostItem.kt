package com.github.rogerp91.android.cleantalks.presentation.common.model

import com.github.rogerp91.android.cleantalks.domain.usecase.CombinedUserPost

/**
 * Created by rpatino on oct/2019
 */

data class PostItem(
    val postId: String,
    val userId: String,
    val title: String,
    val body: String,
    val name: String,
    val username: String,
    val email: String
)

fun CombinedUserPost.mapToPresentation(): PostItem = PostItem(
    post.id,
    user.id,
    post.title,
    post.body,
    user.name,
    user.username,
    user.email
)

fun List<CombinedUserPost>.mapToPresentation(): List<PostItem> = map { it.mapToPresentation() }