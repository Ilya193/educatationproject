package com.xlwe.educatationproject.data.chapters.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.chapters.ChapterData
import com.xlwe.educatationproject.data.chapters.ToChapterMapper
import io.realm.RealmObject

open class ChapterDb : RealmObject(), Abstract.Object<ChapterData, ToChapterMapper> {
    var id: Int = -1
    var bookId: Int = -1

    override fun map(mapper: ToChapterMapper): ChapterData {
        return mapper.map(id, bookId)
    }

}