package com.github.rogerp91.android.cleantalks.data.repository

import com.github.rogerp91.android.cleantalks.data.datasource.UserCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.UserRemoteDataSource
import com.github.rogerp91.android.cleantalks.domain.model.User
import com.github.rogerp91.android.cleantalks.domain.repository.UserRepository
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class UserRepositoryImpl constructor(
    private val cacheDataSource: UserCacheDataSource,
    private val remoteDataSource: UserRemoteDataSource
) : UserRepository {

    override fun get(refresh: Boolean): Single<List<User>> =
        when (refresh) {
            true -> remoteDataSource.get()
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get()
                .onErrorResumeNext { get(true) }
        }

    override fun get(userId: String, refresh: Boolean): Single<User> =
        when (refresh) {
            true -> remoteDataSource.get(userId)
                .flatMap { cacheDataSource.set(it) }
            false -> cacheDataSource.get(userId)
                .onErrorResumeNext { get(userId, true) }
        }
}