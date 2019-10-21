package com.github.rogerp91.android.cleantalks.data.datasource

import com.github.rogerp91.android.cleantalks.domain.model.Comment
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface CommentCacheDataSource {

    fun get(postId: String): Single<List<Comment>>

    fun set(postId: String, list: List<Comment>): Single<List<Comment>>
}

interface CommentRemoteDataSource {

    fun get(postId: String): Single<List<Comment>>
}
