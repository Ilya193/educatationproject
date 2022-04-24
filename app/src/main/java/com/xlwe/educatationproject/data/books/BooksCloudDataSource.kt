package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.data.books.net.BookCloud
import com.xlwe.educatationproject.data.books.net.BooksService

interface BooksCloudDataSource {
    suspend fun fetchBooks(): List<BookCloud>

    class Base(private val service: BooksService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return service.fetchBooks()
        }
    }
}