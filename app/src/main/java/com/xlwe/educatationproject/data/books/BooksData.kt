package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.BooksDomain

sealed class BooksData : Abstract.Object<BooksDomain, BooksDataToDomainMapper> {
    data class Success(private val books: List<BookData>) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(books)
        }
    }

    data class Fail(private val e: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BooksDomain {
            return mapper.map(e)
        }
    }
}