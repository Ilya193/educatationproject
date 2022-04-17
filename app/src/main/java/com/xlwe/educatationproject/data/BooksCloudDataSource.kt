package com.xlwe.educatationproject.data

import com.xlwe.educatationproject.data.net.BookCloud
import com.xlwe.educatationproject.data.net.BooksService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}