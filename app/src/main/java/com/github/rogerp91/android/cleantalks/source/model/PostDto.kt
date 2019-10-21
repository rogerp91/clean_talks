package com.github.rogerp91.android.cleantalks.source.model

import com.github.rogerp91.android.cleantalks.domain.model.Post
import com.squareup.moshi.Json

/**
 * Created by rpatino on oct/2019
 */
data class PostDto(
    @field:Json(name = "userId") val userId: String,
    @field:Json(name = "id") val id: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "body") val body: String
)

fun PostDto.mapToDomain(): Post = Post(userId, id, title, body)

fun List<PostDto>.mapToDomain(): List<Post> = map { it.mapToDomain() }