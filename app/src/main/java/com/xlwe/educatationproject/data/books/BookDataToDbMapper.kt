package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.books.cache.BookDb
import com.xlwe.educatationproject.core.DbWrapper

interface BookDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, name: String, testament: String, db: DbWrapper.Base<BookDb>): BookDb

    class Base : BookDataToDbMapper {
        override fun mapToDb(
            id: Int,
            name: String,
            testament: String,
            db: DbWrapper.Base<BookDb>
        ): BookDb {
            val bookDb = db.createObject(id)
            bookDb.name = name
            bookDb.testament = testament
            return bookDb
        }
    }
}