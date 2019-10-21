package com.github.rogerp91.android.cleantalks.presentation.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.rogerp91.android.cleantalks.domain.usecase.CommentsUseCase
import com.github.rogerp91.android.cleantalks.domain.usecase.UserPostUseCase
import com.github.rogerp91.android.cleantalks.presentation.common.model.CommentItem
import com.github.rogerp91.android.cleantalks.presentation.common.model.PostItem
import com.github.rogerp91.android.cleantalks.presentation.common.model.mapToPresentation
import com.github.rogerp91.android.cleantalks.presentation.common.util.Resource
import com.github.rogerp91.android.cleantalks.presentation.common.util.setError
import com.github.rogerp91.android.cleantalks.presentation.common.util.setLoading
import com.github.rogerp91.android.cleantalks.presentation.common.util.setSuccess
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by rpatino on oct/2019
 */

class PostDetailsViewModel constructor(
    private val userPostUseCase: UserPostUseCase,
    private val commentsUseCase: CommentsUseCase
) : ViewModel() {

    val post = MutableLiveData<PostItem>()
    val comments = MutableLiveData<Resource<List<CommentItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun getPost(ids: UserIdPostId) =
        compositeDisposable.add(userPostUseCase.get(ids.userId, ids.postId, false)
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ post.postValue(it) }, { })
        )

    fun getComments(postId: String, refresh: Boolean = false) =
        compositeDisposable.add(commentsUseCase.get(postId, refresh)
            .doOnSubscribe { comments.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ comments.setSuccess(it) }, { comments.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}

data class UserIdPostId(val userId: String, val postId: String)