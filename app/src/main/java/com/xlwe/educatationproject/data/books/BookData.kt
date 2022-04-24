package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.ToDbMapper
import com.xlwe.educatationproject.data.books.cache.BookDb
import com.xlwe.educatationproject.core.DbWrapper
import com.xlwe.educatationproject.domain.BookDomain

class BookData(
    private val id: Int,
    private val name: String,
    private val testament: String
) : ToDbMapper<BookDb, BookDataToDbMapper>,
    Abstract.Object<BookDomain, BookDataToDomainMapper> {

    override fun map(mapper: BookDataToDomainMapper): BookDomain {
        return mapper.map(id, name)
    }

    override fun mapTo(mapper: BookDataToDbMapper, db: DbWrapper.Base<BookDb>): BookDb {
        return mapper.mapToDb(id, name, testament, db)
    }

    fun matches(temp: TestamentTemp): Boolean {
        return temp.matches(testament)
    }

    fun saveTestament(temp: TestamentTemp) = temp.save(testament)
}

interface TestamentTemp {
    fun save(testament: String)
    fun matches(testament: String): Boolean
    fun isEmpty(): Boolean

    class Base : TestamentTemp {
        private var temp = ""
        override fun save(testament: String) {
            temp = testament
        }

        override fun matches(testament: String): Boolean {
            return temp == testament
        }

        override fun isEmpty(): Boolean {
            return temp.isEmpty()
        }
    }
}