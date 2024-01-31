package com.example.newsapp.presentation.search

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    var searchQuery: String = "",
    var article: Flow<PagingData<Article>>? = null
)