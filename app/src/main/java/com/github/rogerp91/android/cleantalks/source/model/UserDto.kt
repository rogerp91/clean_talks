package com.github.rogerp91.android.cleantalks.source.model

import com.github.rogerp91.android.cleantalks.domain.model.User
import com.squareup.moshi.Json

/**
 * Created by rpatino on oct/2019
 */
data class UserDto(
    @field:Json(name = "id") val id: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "username") val username: String,
    @field:Json(name = "email") val email: String
)

fun UserDto.mapToDomain(): User = User(id, name, username, email)

fun List<UserDto>.mapToDomain(): List<User> = map { it.mapToDomain() }