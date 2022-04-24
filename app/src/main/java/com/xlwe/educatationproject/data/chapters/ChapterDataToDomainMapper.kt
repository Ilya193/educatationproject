package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.domain.chapters.ChapterDomain

interface ChapterDataToDomainMapper : Abstract.Mapper {
    fun map(id: Int): ChapterDomain

    class Base() : ChapterDataToDomainMapper {
        override fun map(id: Int): ChapterDomain {

        }

    }
}