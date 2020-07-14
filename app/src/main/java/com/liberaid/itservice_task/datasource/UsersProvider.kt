package com.liberaid.itservice_task.datasource

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UsersProvider @Inject constructor(private val githubApi: IGithubApi) {

    fun getUsers(since: Int = 0) = githubApi.getUsers(since)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun getUserByUsername(username: String) = githubApi.getUser(username)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}