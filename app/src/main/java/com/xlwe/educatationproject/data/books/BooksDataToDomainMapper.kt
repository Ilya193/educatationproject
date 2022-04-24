package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {
    fun map(books: List<BookData>): BooksDomain
    fun map(e: Exception): BooksDomain
}