package com.xlwe.educatationproject.data

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<Book>): BooksDomain
    fun map(e: Exception): BooksDomain
}