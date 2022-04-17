package com.xlwe.educatationproject.core

import android.app.Application
import com.xlwe.educatationproject.data.BooksCloudDataSource
import com.xlwe.educatationproject.data.BooksCloudMapper
import com.xlwe.educatationproject.data.BooksRepository
import com.xlwe.educatationproject.data.cache.BookCacheMapper
import com.xlwe.educatationproject.data.cache.BooksCacheDataSource
import com.xlwe.educatationproject.data.cache.BooksCacheMapper
import com.xlwe.educatationproject.data.cache.RealmProvider
import com.xlwe.educatationproject.data.net.BookCloudMapper
import com.xlwe.educatationproject.data.net.BooksService
import com.xlwe.educatationproject.domain.BaseBooksDataToDomainMapper
import com.xlwe.educatationproject.domain.BooksInteractor
import com.xlwe.educatationproject.presentation.BaseBooksDomainToUiMapper
import com.xlwe.educatationproject.presentation.BooksCommunication
import com.xlwe.educatationproject.presentation.MainViewModel
import com.xlwe.educatationproject.presentation.ResourceProvider
import io.realm.Realm
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var mainViewModel: MainViewModel

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()

        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val cacheDataSource = BooksCacheDataSource.Base(RealmProvider.Base())
        val booksCloudMapper = BooksCloudMapper.Base(BookCloudMapper.Base())
        val booksCacheMapper = BooksCacheMapper.Base(BookCacheMapper.Base())

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())

        val communication = BooksCommunication.Base()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(communication, ResourceProvider.Base(this)),
            communication
        )
    }
}