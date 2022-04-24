package com.xlwe.educatationproject.data.books.net

import com.google.gson.annotations.SerializedName
import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.ToBookMapper

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
    private val name: String,
    @SerializedName("testament")
    private val testament: String
) : Abstract.Object<BookData, ToBookMapper> {
    override fun map(mapper: ToBookMapper): BookData {
        return mapper.map(id, name, testament)
    }
}