package com.xlwe.educatationproject.data.chapters.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.DbWrapper

interface ChapterDataToDbMapper : Abstract.Mapper {
    fun mapToDb(id: Int, bookId: Int, db: DbWrapper<ChapterDb>): ChapterDb

    class Base(

    ) : ChapterDataToDbMapper {
        override fun mapToDb(id: Int, bookId: Int, db: DbWrapper<ChapterDb>): ChapterDb {
            val chapterDb = db.createObject(id)
            chapterDb.id = id
            chapterDb.bookId = bookId
            return chapterDb
        }

    }
}