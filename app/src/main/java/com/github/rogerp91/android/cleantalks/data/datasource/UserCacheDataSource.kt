package com.github.rogerp91.android.cleantalks.data.datasource

import com.github.rogerp91.android.cleantalks.domain.model.User
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
interface UserCacheDataSource {

    fun get(): Single<List<User>>

    fun set(item: User): Single<User>

    fun get(userId: String): Single<User>

    fun set(list: List<User>): Single<List<User>>
}

interface UserRemoteDataSource {

    fun get(): Single<List<User>>

    fun get(userId: String): Single<User>
}