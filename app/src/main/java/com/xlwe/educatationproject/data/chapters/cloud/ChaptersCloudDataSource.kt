package com.xlwe.educatationproject.data.chapters.cloud

interface ChaptersCloudDataSource {
    suspend fun fetchChapters(bookId: Int): List<ChapterCloud>

    class Base(
        private val service: ChaptersService
    ) : ChaptersCloudDataSource {
        override suspend fun fetchChapters(bookId: Int): List<ChapterCloud> {
            return service.fetchChapters(bookId)
        }
    }
}