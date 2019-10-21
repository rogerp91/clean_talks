package com.github.rogerp91.android.cleantalks.domain.repository

import com.github.rogerp91.android.cleantalks.domain.model.User
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface UserRepository {

    fun get(refresh: Boolean): Single<List<User>>

    fun get(userId: String, refresh: Boolean): Single<User>
}