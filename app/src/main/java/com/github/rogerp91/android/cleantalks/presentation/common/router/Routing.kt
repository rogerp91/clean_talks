package com.github.rogerp91.android.cleantalks.presentation.common.router

import android.content.Intent
import androidx.fragment.app.Fragment

/**
 * Created by rpatino on oct/2019
 */
object Routing : RouterFeature<Intent> {

    const val USER_ID_KEY = "USER_ID_KEY"
    const val POST_ID_KEY = "POST_ID_KEY"

    private const val POST_LIST =
        "com.github.rogerp91.android.cleantalks.presentation.list.PostListActivity"
    private const val POST_DETAILS =
        "com.github.rogerp91.android.cleantalks.presentation.detail.PostDetailsActivity"

    override val dynamicStart: Intent?
        get() = POST_LIST.loadIntentOrNull()

    fun postDetails(userId: String, postId: String): Intent? =
        POST_DETAILS.loadIntentOrNull()
            ?.apply {
                putExtra(USER_ID_KEY, userId)
                putExtra(POST_ID_KEY, postId)
            }
}

internal fun String.loadFragmentOrNull(): Fragment? =
    try {
        this.loadClassOrNull<Fragment>()?.newInstance()
    } catch (e: ClassNotFoundException) {
        null
    }

private val classMap = mutableMapOf<String, Class<*>>()

private inline fun <reified T : Any> Any.castOrNull() = this as? T

internal fun <T> String.loadClassOrNull(): Class<out T>? =
    classMap.getOrPut(this) {
        try {
            Class.forName(this)
        } catch (e: ClassNotFoundException) {
            return null
        }
    }.castOrNull()

private const val PACKAGE_NAME = "com.github.rogerp91.android.cleantalks"

private fun intentTo(className: String): Intent =
    Intent(Intent.ACTION_VIEW).setClassName(PACKAGE_NAME, className)

internal fun String.loadIntentOrNull(): Intent? =
    try {
        Class.forName(this).run { intentTo(this@loadIntentOrNull) }
    } catch (e: ClassNotFoundException) {
        null
    }
