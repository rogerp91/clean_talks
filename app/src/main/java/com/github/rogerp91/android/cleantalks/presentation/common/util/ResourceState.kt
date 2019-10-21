package com.github.rogerp91.android.cleantalks.presentation.common.util

/**
 * Created by rpatino on oct/2019
 */
sealed class ResourceState {
    object LOADING : ResourceState()
    object SUCCESS : ResourceState()
    object ERROR : ResourceState()
}