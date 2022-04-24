package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.presentation.BooksUi

sealed class BooksDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper> {
    class Success(
        private val books: List<BookDomain>,
    ) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(books)
        }
    }

    class Fail(private val errorType: ErrorType) : BooksDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(errorType)
        }
    }
}
