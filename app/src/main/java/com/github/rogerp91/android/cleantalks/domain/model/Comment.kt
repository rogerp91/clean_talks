package com.github.rogerp91.android.cleantalks.domain.model

/**
 * Created by rpatino on oct/2019
 */
data class Comment(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)