package com.github.rogerp91.android.cleantalks.data.datasource

import com.github.rogerp91.android.cleantalks.source.model.CommentDto
import com.github.rogerp91.android.cleantalks.source.model.PostDto
import com.github.rogerp91.android.cleantalks.source.model.UserDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by rpatino on oct/2019
 */

interface PostsApi {

    @GET("posts/")
    fun getPosts(): Single<List<PostDto>>

    @GET("posts/{id}")
    fun getPost(@Path("id") postId: String): Single<PostDto>
}

interface UsersApi {

    @GET("users/")
    fun getUsers(): Single<List<UserDto>>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String): Single<UserDto>
}

interface CommentsApi {

    @GET("comments/")
    fun getComments(@Query("postId") postId: String): Single<List<CommentDto>>
}