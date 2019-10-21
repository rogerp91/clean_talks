package com.github.rogerp91.android.cleantalks.presentation.common.util

/**
 * Created by rpatino on oct/2019
 */
data class Resource<out T> constructor(
    val state: ResourceState,
    val data: T? = null,
    val message: String? = null
)