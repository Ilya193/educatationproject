package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.domain.BooksDomainToUiMapper
import com.xlwe.educatationproject.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
): BooksDomainToUiMapper {
    override fun map(books: List<Book>): BooksUi {
        return BooksUi.Success(communication, books)
    }

    override fun map(errorType: ErrorType): BooksUi {
        return BooksUi.Fail(communication, errorType, resourceProvider)
    }

}