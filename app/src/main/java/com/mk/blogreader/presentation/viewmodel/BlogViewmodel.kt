package com.mk.blogreader.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.mk.blogreader.data.model.BlogItem
import com.mk.blogreader.data.repository.BlogRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class BlogViewmodel @Inject constructor(
    private val repo : BlogRepository
) : ViewModel(){

    val blogsPost  = repo.getBlogsPager().flow.cachedIn(viewModelScope)

    private var _selectedBlog   = MutableStateFlow<BlogItem?>(null)
    val selectedBlog = _selectedBlog

    fun selectedBlog(blog : BlogItem){
        _selectedBlog.value = blog
    }

}