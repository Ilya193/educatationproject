package com.xlwe.educatationproject.data.books.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.ToBookMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), Abstract.Object<BookData, ToBookMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""
    var testament: String = ""

    override fun map(mapper: ToBookMapper): BookData {
        return mapper.map(id, name, testament)
    }
}