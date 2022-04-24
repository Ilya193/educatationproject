package com.xlwe.educatationproject.data.chapters.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.chapters.ChapterData
import com.xlwe.educatationproject.data.chapters.ToChapterMapper

interface ChaptersCacheMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterDb>): List<ChapterData>

    class Base(
        private val mapper: ToChapterMapper
    ) : ChaptersCacheMapper {
        override fun map(chapters: List<ChapterDb>): List<ChapterData> {
            return chapters.map { chapterDb ->
                chapterDb.map(mapper)
            }
        }
    }
}