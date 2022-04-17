package com.xlwe.educatationproject.data

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book
import com.xlwe.educatationproject.data.net.BookCloud
import com.xlwe.educatationproject.data.net.BookCloudMapper

interface BooksCloudMapper : Abstract.Mapper {
    fun map(cloudList: List<BookCloud>): List<Book>

    class Base(private val bookMapper: BookCloudMapper) : BooksCloudMapper {
        override fun map(cloudList: List<BookCloud>): List<Book> {
            return cloudList.map { bookCloud ->
                bookCloud.map(bookMapper)
            }
        }
    }
}