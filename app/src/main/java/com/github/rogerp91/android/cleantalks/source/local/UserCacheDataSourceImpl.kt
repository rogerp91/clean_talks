package com.github.rogerp91.android.cleantalks.source.local

import com.github.rogerp91.android.cleantalks.data.datasource.UserCacheDataSource
import com.github.rogerp91.android.cleantalks.domain.model.User
import io.reactivex.Single

/**
 * Created by rpatino on oct/2019
 */
class UserCacheDataSourceImpl constructor(
    private val cache: ReactiveCache<List<User>>
) : UserCacheDataSource {

    val key = "User List"

    override fun get(): Single<List<User>> =
        cache.load(key)

    override fun set(list: List<User>): Single<List<User>> =
        cache.save(key, list)

    override fun get(userId: String): Single<User> =
        cache.load(key)
            .map { it.first { it.id == userId } }

    override fun set(item: User): Single<User> =
        cache.load(key)
            .map { it.filter { it.id != item.id }.plus(item) }
            .flatMap { set(it) }
            .map { item }
}