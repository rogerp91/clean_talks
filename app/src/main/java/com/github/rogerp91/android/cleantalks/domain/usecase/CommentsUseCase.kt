package com.github.rogerp91.android.cleantalks.domain.usecase

import com.github.rogerp91.android.cleantalks.domain.model.Comment
import com.github.rogerp91.android.cleantalks.domain.repository.CommentRepository
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class CommentsUseCase constructor(
    private val commentRepository: CommentRepository
) {

    fun get(postId: String, refresh: Boolean): Single<List<Comment>> =
        commentRepository.get(postId, refresh)
}