package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.chapters.ChaptersDomain
import java.lang.Exception

sealed class ChaptersData : Abstract.Object<ChaptersDomain, ChaptersDataToDomainMapper> {

    data class Success(
        private val chapters: List<ChapterData>
    ) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain {
            return mapper.map(chapters)
        }
    }

    data class Fail(
        private val e: Exception
    ) : ChaptersData() {
        override fun map(mapper: ChaptersDataToDomainMapper): ChaptersDomain {
            return mapper.map(e)
        }
    }

}
