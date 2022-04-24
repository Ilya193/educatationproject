package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.ToDbMapper
import com.xlwe.educatationproject.core.DbWrapper
import com.xlwe.educatationproject.data.chapters.cache.ChapterDataToDbMapper
import com.xlwe.educatationproject.data.chapters.cache.ChapterDb
import com.xlwe.educatationproject.domain.chapters.ChapterDomain

data class ChapterData(
    private val id: Int,
    private val bookId: Int
) : ToDbMapper<ChapterDb, ChapterDataToDbMapper>,
    Abstract.Object<ChapterDomain, ChapterDataToDomainMapper> {

    override fun map(mapper: ChapterDataToDomainMapper): ChapterDomain {
        return mapper.map(id, bookId)
    }

    override fun mapTo(mapper: ChapterDataToDbMapper, db: DbWrapper<ChapterDb>): ChapterDb {
        return mapper.mapToDb(id, bookId, db)
    }

}