package com.xlwe.educatationproject.data.net

import com.google.gson.annotations.SerializedName
import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Book

/*
{
    "id": 1,
    "name": "Genesis",
    "testament": "OT",
    "genre": {
    "id": 1,
    "name": "Law"
    }
}
*/

data class BookCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("name")
    private val name: String
) : Abstract.Object<Book, BookCloudMapper>() {
    override fun map(mapper: BookCloudMapper): Book {
        return mapper.map(id, name)
    }
}