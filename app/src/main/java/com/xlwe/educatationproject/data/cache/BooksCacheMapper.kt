package com.xlwe.educatationproject.data.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book

interface BooksCacheMapper : Abstract.Mapper {
    fun map(books: List<BookDb>) : List<Book>

    class Base(private val mapper: BookCacheMapper) : BooksCacheMapper {
        override fun map(books: List<BookDb>): List<Book> {
            return books.map { bookDb ->
                bookDb.map(mapper)
            }
        }
    }
}