package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.data.BooksDataToDomainMapper
import com.xlwe.educatationproject.data.BooksRepository

interface BooksInteractor {
    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val mapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            return booksRepository.fetchBooks().map(mapper)
        }
    }
}