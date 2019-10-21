package com.github.rogerp91.android.cleantalks.module

import com.github.rogerp91.android.cleantalks.BuildConfig
import com.github.rogerp91.android.cleantalks.data.datasource.CommentCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.CommentRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.CommentsApi
import com.github.rogerp91.android.cleantalks.data.datasource.PostCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.PostRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.PostsApi
import com.github.rogerp91.android.cleantalks.data.datasource.UserCacheDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.UserRemoteDataSource
import com.github.rogerp91.android.cleantalks.data.datasource.UsersApi
import com.github.rogerp91.android.cleantalks.data.datasource.createNetworkClient
import com.github.rogerp91.android.cleantalks.data.repository.CommentRepositoryImpl
import com.github.rogerp91.android.cleantalks.data.repository.PostRepositoryImpl
import com.github.rogerp91.android.cleantalks.data.repository.UserRepositoryImpl
import com.github.rogerp91.android.cleantalks.domain.model.Comment
import com.github.rogerp91.android.cleantalks.domain.repository.CommentRepository
import com.github.rogerp91.android.cleantalks.domain.repository.PostRepository
import com.github.rogerp91.android.cleantalks.domain.repository.UserRepository
import com.github.rogerp91.android.cleantalks.domain.usecase.CommentsUseCase
import com.github.rogerp91.android.cleantalks.domain.usecase.UserPostUseCase
import com.github.rogerp91.android.cleantalks.domain.usecase.UsersPostsUseCase
import com.github.rogerp91.android.cleantalks.presentation.detail.PostDetailsViewModel
import com.github.rogerp91.android.cleantalks.presentation.list.PostListViewModel
import com.github.rogerp91.android.cleantalks.source.local.CommentCacheDataSourceImpl
import com.github.rogerp91.android.cleantalks.source.local.PostCacheDataSourceImpl
import com.github.rogerp91.android.cleantalks.source.local.ReactiveCache
import com.github.rogerp91.android.cleantalks.source.local.UserCacheDataSourceImpl
import com.github.rogerp91.android.cleantalks.source.model.PostDto
import com.github.rogerp91.android.cleantalks.source.model.UserDto
import com.github.rogerp91.android.cleantalks.source.remote.CommentRemoteDataSourceImpl
import com.github.rogerp91.android.cleantalks.source.remote.PostRemoteDataSourceImpl
import com.github.rogerp91.android.cleantalks.source.remote.UserRemoteDataSourceImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by rpatino on oct/2019
 */

fun injectModule() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule,
        cacheModule
    )
}

val viewModelModule: Module = module {
    viewModel { PostListViewModel(usersPostsUseCase = get()) }
    viewModel { PostDetailsViewModel(userPostUseCase = get(), commentsUseCase = get()) }
}

val useCaseModule: Module = module {
    factory { UsersPostsUseCase(userRepository = get(), postRepository = get()) }
    factory { UserPostUseCase(userRepository = get(), postRepository = get()) }
    factory { CommentsUseCase(commentRepository = get()) }
}

val repositoryModule: Module = module {
    single {
        UserRepositoryImpl(
            cacheDataSource = get(),
            remoteDataSource = get()
        ) as UserRepository
    }
    single {
        PostRepositoryImpl(
            cacheDataSource = get(),
            remoteDataSource = get()
        ) as PostRepository
    }
    single {
        CommentRepositoryImpl(
            cacheDataSource = get(),
            remoteDataSource = get()
        ) as CommentRepository
    }
}

val dataSourceModule: Module = module {
    single { UserCacheDataSourceImpl(cache = get(USER_CACHE)) as UserCacheDataSource }
    single { UserRemoteDataSourceImpl(api = usersApi) as UserRemoteDataSource }
    single { PostCacheDataSourceImpl(cache = get(POST_CACHE)) as PostCacheDataSource }
    single { PostRemoteDataSourceImpl(api = postsApi) as PostRemoteDataSource }
    single { CommentCacheDataSourceImpl(cache = get(COMMENT_CACHE)) as CommentCacheDataSource }
    single { CommentRemoteDataSourceImpl(api = commentsApi) as CommentRemoteDataSource }
}

val networkModule: Module = module {
    single { usersApi }
    single { postsApi }
    single { commentsApi }
}

val cacheModule: Module = module {
    single(name = USER_CACHE) { ReactiveCache<List<UserDto>>() }
    single(name = POST_CACHE) { ReactiveCache<List<PostDto>>() }
    single(name = COMMENT_CACHE) { ReactiveCache<List<Comment>>() }
}

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val postsApi: PostsApi = retrofit.create(PostsApi::class.java)
private val usersApi: UsersApi = retrofit.create(UsersApi::class.java)
private val commentsApi: CommentsApi = retrofit.create(CommentsApi::class.java)

private const val USER_CACHE = "USER_CACHE"
private const val POST_CACHE = "POST_CACHE"
private const val COMMENT_CACHE = "COMMENT_CACHE"