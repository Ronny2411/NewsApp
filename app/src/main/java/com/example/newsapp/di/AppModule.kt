package com.example.newsapp.di

import android.app.Application
import androidx.room.Room
import com.example.newsapp.data.local.NewsDao
import com.example.newsapp.data.local.NewsDatabase
import com.example.newsapp.data.local.NewsTypeConverter
import com.example.newsapp.data.manager.LocalUserManagerImpl
import com.example.newsapp.data.remote.NewsApi
import com.example.newsapp.data.repository.NewsRepositoryImpl
import com.example.newsapp.domain.manager.LocalUserManager
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.example.newsapp.domain.usecases.app_entry.ReadAppEntry
import com.example.newsapp.domain.usecases.app_entry.SaveAppEntry
import com.example.newsapp.domain.usecases.news.DeleteArticleUseCase
import com.example.newsapp.domain.usecases.news.GetArticlesUseCase
import com.example.newsapp.domain.usecases.news.GetNewsUseCase
import com.example.newsapp.domain.usecases.news.GetSelectedArticleUseCase
import com.example.newsapp.domain.usecases.news.NewsUseCases
import com.example.newsapp.domain.usecases.news.SearchNewsUseCase
import com.example.newsapp.domain.usecases.news.UpsertArticleUseCase
import com.example.newsapp.util.Constants.BASE_URL
import com.example.newsapp.util.Constants.NEWS_DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application : Application
    ):LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCase(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
            readAppEntry = ReadAppEntry(localUserManager),
            saveAppEntry = SaveAppEntry(localUserManager)
        )

    @Provides
    @Singleton
    fun providesNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ): NewsRepository = NewsRepositoryImpl(newsApi = newsApi, newsDao = newsDao)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ) = NewsUseCases(
        getNewsUseCase = GetNewsUseCase(newsRepository),
        searchNewsUseCases = SearchNewsUseCase(newsRepository),
        upsertArticleUseCase = UpsertArticleUseCase(newsRepository),
        deleteArticleUseCase = DeleteArticleUseCase(newsRepository),
        getArticlesUseCase = GetArticlesUseCase(newsRepository),
        getSelectedArticleUseCase = GetSelectedArticleUseCase(newsRepository)
    )

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DB_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ): NewsDao = newsDatabase.newsDao

}