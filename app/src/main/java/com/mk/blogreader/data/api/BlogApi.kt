package com.mk.blogreader.data.api

import com.mk.blogreader.data.model.BlogItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface BlogApi {

    @GET("posts")
    suspend fun getBlogPost(
        @Query("per_page") perPage : Int = 10 ,
        @Query("page") page : Int
    ): Response<List<BlogItem>>
}