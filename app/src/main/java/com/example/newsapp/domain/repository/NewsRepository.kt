package com.example.newsapp.domain.repository

import androidx.paging.PagingData
import com.example.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(source: List<String>): Flow<PagingData<Article>>
    fun searchNews(searchQuery: String, source: List<String>): Flow<PagingData<Article>>
    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun getArticles(): Flow<List<Article>>
    suspend fun getSelectedArticle(url: String): Article?
}