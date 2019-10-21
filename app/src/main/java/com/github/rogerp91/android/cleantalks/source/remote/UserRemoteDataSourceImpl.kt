package com.github.rogerp91.android.cleantalks.source.remote

import com.github.rogerp91.android.cleantalks.data.datasource.UserRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.UsersApi
import com.github.rogerp91.android.cleantalks.domain.model.User
import com.github.rogerp91.android.cleantalks.source.model.mapToDomain
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class UserRemoteDataSourceImpl constructor(
    private val api: UsersApi
) : UserRemoteDataSource {

    override fun get(): Single<List<User>> =
        api.getUsers()
            .map { it.mapToDomain() }

    override fun get(userId: String): Single<User> =
        api.getUser(userId)
            .map { it.mapToDomain() }
}