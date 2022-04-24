package com.xlwe.educatationproject.data.chapters

import com.xlwe.educatationproject.data.chapters.cache.ChaptersCacheDataSource
import com.xlwe.educatationproject.data.chapters.cache.ChaptersCacheMapper
import com.xlwe.educatationproject.data.chapters.cloud.ChaptersCloudDataSource
import com.xlwe.educatationproject.data.chapters.cloud.ChaptersCloudMapper
import java.lang.Exception

interface ChaptersRepository {
    suspend fun fetchChapters(bookId: Int): ChaptersData

    class Base(
        private val cloudDataSource: ChaptersCloudDataSource,
        private val cacheDataSource: ChaptersCacheDataSource,
        private val chaptersCloudMapper: ChaptersCloudMapper,
        private val chaptersCacheMapper: ChaptersCacheMapper,
    ) : ChaptersRepository {
        override suspend fun fetchChapters(bookId: Int): ChaptersData {
            return try {
                val chaptersCacheList = cacheDataSource.fetchChapters(bookId)
                if (chaptersCacheList.isEmpty()) {
                    val chaptersCloudList = cloudDataSource.fetchChapters(bookId)
                    val chapters = chaptersCloudMapper.map(chaptersCloudList, bookId)
                    cacheDataSource.saveChapters(bookId, chapters)
                    ChaptersData.Success(chapters)
                } else {
                    ChaptersData.Success(chaptersCacheMapper.map(chaptersCacheList))
                }
            } catch (e: Exception) {
                ChaptersData.Fail(e)
            }
        }
    }
}