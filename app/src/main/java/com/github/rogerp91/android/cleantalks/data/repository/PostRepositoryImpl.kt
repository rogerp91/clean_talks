package com.github.rogerp91.android.cleantalks.data.repository

import com.github.rogerp91.android.cleantalks.data.datasource.PostCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.PostRemoteDataSource
import com.github.rogerp91.android.cleantalks.domain.model.Post
import com.github.rogerp91.android.cleantalks.domain.repository.PostRepository
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class PostRepositoryImpl constructor(
    private val cacheDataSource: PostCacheDataSource,
    private val remoteDataSource: PostRemoteDataSource
) : PostRepository {

    override fun get(refresh: Boolean): Single<List<Post>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext { get(true) }
        }

    override fun get(postId: String, refresh: Boolean): Single<Post> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}