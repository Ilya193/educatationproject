package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.chapters.ChaptersDomain

interface ChaptersDataToDomainMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterData>): ChaptersDomain
    fun map(e: Exception): ChaptersDomain
}