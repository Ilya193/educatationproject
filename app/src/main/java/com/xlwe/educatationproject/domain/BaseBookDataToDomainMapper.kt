package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.data.books.BookDataToDomainMapper

class BaseBookDataToDomainMapper : BookDataToDomainMapper {
    override fun map(id: Int, name: String): BookDomain {
        return BookDomain.Base(id, name)
    }
}