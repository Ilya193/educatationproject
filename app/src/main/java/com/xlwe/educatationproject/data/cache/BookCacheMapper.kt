package com.xlwe.educatationproject.data.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book

interface BookCacheMapper : Abstract.Mapper {
    fun map(bookDb: BookDb) : Book

    class Base : BookCacheMapper {
        override fun map(bookDb: BookDb): Book {
            return Book(bookDb.id, bookDb.name)
        }

    }
}