package com.xlwe.educatationproject.data.books.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.ToBookMapper

interface BooksCacheMapper : Abstract.Mapper {
    fun map(books: List<Abstract.Object<BookData, ToBookMapper>>) : List<BookData>

    class Base(private val mapper: ToBookMapper) : BooksCacheMapper {
        override fun map(books: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData> {
            return books.map { bookDb ->
                bookDb.map(mapper)
            }
        }
    }
}