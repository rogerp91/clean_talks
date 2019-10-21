package com.github.rogerp91.android.cleantalks.domain.repository

import com.github.rogerp91.android.cleantalks.domain.model.Comment
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface CommentRepository {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>>
}