package com.mk.blogreader.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mk.blogreader.data.api.BlogApi
import com.mk.blogreader.data.model.BlogItem
import com.mk.blogreader.data.paging.BlogPagingSource
import javax.inject.Inject

class BlogRepository @Inject constructor(
    private val api  : BlogApi
) {

    fun getBlogsPager() : Pager<Int, BlogItem> {
       return Pager(
           config = PagingConfig(pageSize = 10),
           pagingSourceFactory = { BlogPagingSource(api)}
       )
    }
}