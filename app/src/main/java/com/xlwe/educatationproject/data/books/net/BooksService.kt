package com.xlwe.educatationproject.data.books.net

import retrofit2.http.GET

interface BooksService {
    @GET("books")
    suspend fun fetchBooks(): List<BookCloud>
}