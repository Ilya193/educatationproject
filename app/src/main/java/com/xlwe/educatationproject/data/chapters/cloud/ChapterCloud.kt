package com.xlwe.educatationproject.data.chapters.cloud

import com.google.gson.annotations.SerializedName
import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.chapters.ChapterData
import com.xlwe.educatationproject.data.chapters.ToChapterMapper

data class ChapterCloud(
    @SerializedName("id")
    private val id: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper) = throw IllegalStateException("can't be used")

    fun map(bookId: Int, mapper: ToChapterMapper): ChapterData {
        return mapper.map(id, bookId)
    }
}

data class ChapterCloudWrapper(
    private val chapterCloud: ChapterCloud,
    private val bookId: Int
) : Abstract.Object<ChapterData, ToChapterMapper> {
    override fun map(mapper: ToChapterMapper): ChapterData {
        return chapterCloud.map(bookId, mapper)
    }

}