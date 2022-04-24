package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.BookDomain

interface BookDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookDomain
}