package com.github.rogerp91.android.cleantalks.data.repository

import com.github.rogerp91.android.cleantalks.data.datasource.CommentCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.CommentRemoteDataSource
import com.github.rogerp91.android.cleantalks.domain.model.Comment
import com.github.rogerp91.android.cleantalks.domain.repository.CommentRepository
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class CommentRepositoryImpl constructor(
    private val cacheDataSource: CommentCacheDataSource,
    private val remoteDataSource: CommentRemoteDataSource
) : CommentRepository {

    override fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        when (refresh) {
            true -> remoteDataSource.get(postId)
                .flatMap { cacheDataSource.set(postId, it) }
            false -> cacheDataSource.get(postId)
                .onErrorResumeNext { get(postId, true) }
        }
}