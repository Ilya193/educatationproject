package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.core.Abstract

sealed class BooksUi : Abstract.Object<Unit, BooksCommunication> {

    abstract fun cache(uiDataCache: UiDataCache): BooksUi

    data class Base(
        private val books: List<BookUi>
    ) : BooksUi() {
        override fun map(mapper: BooksCommunication) {
            mapper.map(books)
        }

        override fun cache(uiDataCache: UiDataCache): BooksUi {
            return uiDataCache.cache(books)
        }
    }
}
