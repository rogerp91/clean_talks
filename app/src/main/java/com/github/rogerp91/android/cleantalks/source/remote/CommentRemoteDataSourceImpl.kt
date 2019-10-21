package com.github.rogerp91.android.cleantalks.source.remote

import com.github.rogerp91.android.cleantalks.data.datasource.CommentRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.CommentsApi
import com.github.rogerp91.android.cleantalks.domain.model.Comment
import com.github.rogerp91.android.cleantalks.source.model.mapToDomain
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class CommentRemoteDataSourceImpl constructor(
    private val api: CommentsApi
) : CommentRemoteDataSource {

    override fun get(postId: String): Single<List<Comment>> =
        api.getComments(postId)
            .map { it.mapToDomain() }
}