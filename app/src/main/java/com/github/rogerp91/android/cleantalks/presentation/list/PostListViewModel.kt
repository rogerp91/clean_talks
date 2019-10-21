package com.github.rogerp91.android.cleantalks.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.rogerp91.android.cleantalks.domain.usecase.UsersPostsUseCase
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
class PostListViewModel constructor(
    private val usersPostsUseCase: UsersPostsUseCase
) : ViewModel() {

    val posts = MutableLiveData<Resource<List<PostItem>>>()
    private val compositeDisposable = CompositeDisposable()

    fun get(refresh: Boolean = false) =
        compositeDisposable.add(usersPostsUseCase.get(refresh)
            .doOnSubscribe { posts.setLoading() }
            .subscribeOn(Schedulers.io())
            .map { it.mapToPresentation() }
            .subscribe({ posts.setSuccess(it) }, { posts.setError(it.message) })
        )

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}