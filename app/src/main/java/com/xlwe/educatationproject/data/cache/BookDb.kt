package com.xlwe.educatationproject.data.cache

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BookDb : RealmObject(), Abstract.Mapable<Book, BookCacheMapper> {
    @PrimaryKey
    var id: Int = -1
    var name: String = ""

    override fun map(mapper: BookCacheMapper): Book {
        return Book(id, name)
    }
}