package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {
    fun map(books: List<BookDomain>): BooksUi
    fun map(errorType: ErrorType): BooksUi
}