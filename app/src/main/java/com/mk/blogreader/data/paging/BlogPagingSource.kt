package com.mk.blogreader.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mk.blogreader.data.api.BlogApi
import com.mk.blogreader.data.model.BlogItem

class BlogPagingSource(
    private val api: BlogApi
) : PagingSource<Int, BlogItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BlogItem> {
        return try {
            val page = params.key ?: 1
            val response = api.getBlogPost(perPage = 10, page = page)

            if (response.isSuccessful){
                val blogs = response.body() ?: emptyList()
                LoadResult.Page(
                    data = blogs,
                    prevKey = if (page == 1)  null else page - 1,
                    nextKey = if (blogs.isEmpty()) null else page + 1
                )
            }else{
                LoadResult.Error(Exception("API Error: ${response.code()}"))
            }



        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BlogItem>): Int? {
        val anchorPosition  = state.anchorPosition ?: return null

        val page  = state.closestPageToPosition(anchorPosition = anchorPosition)

        return page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
    }

}