package com.example.newsapp.domain.usecases.news

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val searchNewsUseCases: SearchNewsUseCase,
    val upsertArticleUseCase: UpsertArticleUseCase,
    val deleteArticleUseCase: DeleteArticleUseCase,
    val getArticlesUseCase: GetArticlesUseCase,
    val getSelectedArticleUseCase: GetSelectedArticleUseCase
)
