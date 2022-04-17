package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(books: List<Book>): BooksDomain {
        return BooksDomain.Success(books)
    }

    override fun map(e: Exception): BooksDomain {
        return BooksDomain.Fail(e)
    }

}