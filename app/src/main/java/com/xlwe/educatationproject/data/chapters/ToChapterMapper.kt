package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.core.Abstract

interface ToChapterMapper : Abstract.Mapper {
    fun map(id: Int, bookId: Int): ChapterData

    class Base : ToChapterMapper {
        override fun map(id: Int, bookId: Int): ChapterData {
            return ChapterData(id, bookId)
        }
    }
}