package com.xlwe.educatationproject.data.books

import com.xlwe.educatationproject.core.Abstract

interface ToBookMapper : Abstract.Mapper {
    fun map(id: Int, name: String, testament: String): BookData

    class Base : ToBookMapper {
        override fun map(id: Int, name: String, testament: String): BookData {
            return BookData(id, name, testament)
        }
    }
}