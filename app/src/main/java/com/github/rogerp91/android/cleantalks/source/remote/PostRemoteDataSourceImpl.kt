package com.github.rogerp91.android.cleantalks.source.remote

import com.github.rogerp91.android.cleantalks.data.datasource.PostRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.PostsApi
import com.github.rogerp91.android.cleantalks.domain.model.Post
import com.github.rogerp91.android.cleantalks.source.model.mapToDomain
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class PostRemoteDataSourceImpl constructor(
    private val api: PostsApi
) : PostRemoteDataSource {

    override fun get(): Single<List<Post>> =
        api.getPosts()
            .map { it.mapToDomain() }

    override fun get(postId: String): Single<Post> =
        api.getPost(postId)
            .map { it.mapToDomain() }
}