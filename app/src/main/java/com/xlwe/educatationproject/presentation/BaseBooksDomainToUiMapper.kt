package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.R
import com.xlwe.educatationproject.domain.BookDomain
import com.xlwe.educatationproject.domain.BookDomainToUiMapper
import com.xlwe.educatationproject.domain.BooksDomainToUiMapper
import com.xlwe.educatationproject.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val bookMapper: BookDomainToUiMapper
) : BooksDomainToUiMapper {
    override fun map(books: List<BookDomain>): BooksUi {
        val booksUi = books.map {
            it.map(bookMapper)
        }
        return BooksUi.Base(booksUi)
    }

    override fun map(errorType: ErrorType): BooksUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return BooksUi.Base(listOf(BookUi.Fail(message)))
    }

}