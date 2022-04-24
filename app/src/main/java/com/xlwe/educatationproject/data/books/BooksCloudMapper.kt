package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract

interface BooksCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData>

    class Base(private val bookMapper: ToBookMapper) : BooksCloudMapper {
        override fun map(cloudList: List<Abstract.Object<BookData, ToBookMapper>>): List<BookData> {
            return cloudList.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
        }
    }
}