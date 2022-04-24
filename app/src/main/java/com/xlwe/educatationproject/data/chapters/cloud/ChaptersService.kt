package com.xlwe.educatationproject.data.chapters.cloud

import retrofit2.http.GET
import retrofit2.http.Path

interface ChaptersService {
    @GET("books/{id}/chapters")
    suspend fun fetchChapters(
        @Path("id") bookId: Int
    ) : List<ChapterCloud>
}