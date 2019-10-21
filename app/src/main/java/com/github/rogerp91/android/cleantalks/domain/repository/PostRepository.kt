package com.github.rogerp91.android.cleantalks.domain.repository

import com.github.rogerp91.android.cleantalks.domain.model.Post
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface PostRepository {

    fun get(refresh: Boolean): Single<List<Post>>

    fun get(postId: String, refresh: Boolean): Single<Post>
}