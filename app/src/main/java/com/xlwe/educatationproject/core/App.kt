package com.xlwe.educatationproject.core

import android.app.Application
import com.xlwe.educatationproject.data.books.*
import com.xlwe.educatationproject.data.books.cache.BooksCacheDataSource
import com.xlwe.educatationproject.data.books.cache.BooksCacheMapper
import com.xlwe.educatationproject.data.books.cache.RealmProvider
import com.xlwe.educatationproject.data.books.net.BooksService
import com.xlwe.educatationproject.domain.BaseBookDataToDomainMapper
import com.xlwe.educatationproject.domain.BaseBooksDataToDomainMapper
import com.xlwe.educatationproject.domain.BooksInteractor
import com.xlwe.educatationproject.presentation.*
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(BooksService::class.java)

        val cloudDataSource = BooksCloudDataSource.Base(service)
        val realmProvider = RealmProvider.Base()
        val cacheDataSource =
            BooksCacheDataSource.Base(realmProvider, BookDataToDbMapper.Base())
        val toBookMapper = ToBookMapper.Base()
        val booksCloudMapper = BooksCloudMapper.Base(toBookMapper)
        val booksCacheMapper = BooksCacheMapper.Base(toBookMapper)

        val booksRepository = BooksRepository.Base(
            cloudDataSource,
            cacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )

        val booksInteractor = BooksInteractor.Base(
            booksRepository,
            BaseBooksDataToDomainMapper(BaseBookDataToDomainMapper())
        )

        val communication = BooksCommunication.Base()
        val resourceProvider = ResourceProvider.Base(this)
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(resourceProvider, BaseBookDomainToUiMapper(resourceProvider)),
            communication,
            UiDataCache.Base(IdCache.Base(this))
        )
    }
}