package com.github.rogerp91.android.cleantalks.data.datasource

import com.github.rogerp91.android.cleantalks.domain.model.Post
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface PostCacheDataSource {

    fun get(): Single<List<Post>>

    fun set(list: List<Post>): Single<List<Post>>

    fun get(postId: String): Single<Post>

    fun set(item: Post): Single<Post>
}

interface PostRemoteDataSource {

    fun get(): Single<List<Post>>

    fun get(postId: String): Single<Post>
}