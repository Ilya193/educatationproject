package com.xlwe.educatationproject.data.net

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book

interface BookCloudMapper : Abstract.Mapper {
    fun map(id: Int, name: String): Book

    class Base : BookCloudMapper {
        override fun map(id: Int, name: String): Book {
            return Book(id, name)
        }
    }
}