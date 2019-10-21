package com.github.rogerp91.android.cleantalks.presentation.common.util

import androidx.lifecycle.MutableLiveData

/**
 * Created by rpatino on oct/2019
 */
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) =
    postValue(Resource(ResourceState.SUCCESS, data))

fun <T> MutableLiveData<Resource<T>>.setLoading() =
    postValue(Resource(ResourceState.LOADING, value?.data))

fun <T> MutableLiveData<Resource<T>>.setError(message: String? = null) =
    postValue(Resource(ResourceState.ERROR, value?.data, message))