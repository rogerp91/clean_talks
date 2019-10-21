package com.github.rogerp91.android.cleantalks.source.local

import com.github.rogerp91.android.cleantalks.data.datasource.CommentCacheDataSource
import com.github.rogerp91.android.cleantalks.domain.model.Comment
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class CommentCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<Comment>>
) : CommentCacheDataSource {

    val key = "Comment List"

    override fun get(postId: String): Single<List<Comment>> =
        cache.load(key + postId)

    override fun set(postId: String, list: List<Comment>): Single<List<Comment>> =
        cache.save(key + postId, list)
}