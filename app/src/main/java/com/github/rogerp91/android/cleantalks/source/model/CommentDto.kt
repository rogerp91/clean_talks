package com.github.rogerp91.android.cleantalks.source.model

import com.github.rogerp91.android.cleantalks.domain.model.Comment
import com.squareup.moshi.Json

/**
 * Created by rpatino on oct/2019
 */

data class CommentDto(
    @field:Json(name = "postId") val postId: String,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "body") val body: String
)

fun CommentDto.mapToDomain(): Comment = Comment(postId, id, name, email, body)

fun List<CommentDto>.mapToDomain(): List<Comment> = map { it.mapToDomain() }