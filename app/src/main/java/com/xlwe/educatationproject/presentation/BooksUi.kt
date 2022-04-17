package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.R
import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val communication: BooksCommunication,
        private val books: List<Book>
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            communication.show(books)
        }
    }

    class Fail(
        private val communication: BooksCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                ErrorType.GENERIC_ERROR -> R.string.something_went_wrong
            }
            communication.show(resourceProvider.getString(messageId))
        }

    }
}
