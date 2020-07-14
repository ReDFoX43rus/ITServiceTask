package com.liberaid.itservice_task.datasource

import com.liberaid.itservice_task.objects.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IGithubApi {

    @GET("/users")
    fun getUsers(@Query("since") since: Int): Single<List<User>>

    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<User>

}