package com.xlwe.educatationproject.data.chapters.cloud

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.chapters.ChapterData
import com.xlwe.educatationproject.data.chapters.ToChapterMapper

interface ChaptersCloudMapper : Abstract.Mapper {
    fun map(chapters: List<ChapterCloud>, bookId: Int): List<ChapterData>

    class Base(
        private val chapterMapper: ToChapterMapper
    ) : ChaptersCloudMapper {
        override fun map(chapters: List<ChapterCloud>, bookId: Int): List<ChapterData> {
            return chapters.map { chapterCloud ->
                ChapterCloudWrapper(chapterCloud, bookId).map(chapterMapper)
            }
        }

    }
}