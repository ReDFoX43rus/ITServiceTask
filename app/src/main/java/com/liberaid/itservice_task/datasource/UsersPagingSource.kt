package com.liberaid.itservice_task.datasource

import androidx.paging.rxjava2.RxPagingSource
import com.liberaid.itservice_task.objects.User
import io.reactivex.Single

class UsersPagingSource(private val usersProvider: UsersProvider, private val onErrorHandler: (Throwable) -> Unit) : RxPagingSource<Int, User>() {

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, User>> {
        val since = params.key ?: 0

        return usersProvider.getUsers(since)
            .doOnError { onErrorHandler(it) }
            .onErrorReturn { emptyList() }
            .map { users ->
                LoadResult.Page(users, null, users.lastOrNull()?.id)
            }
    }
}