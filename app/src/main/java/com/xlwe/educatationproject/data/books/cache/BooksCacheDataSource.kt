package com.xlwe.educatationproject.data.books.cache

import com.xlwe.educatationproject.core.DbWrapper
import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.BookDataToDbMapper
import io.realm.Realm

interface BooksCacheDataSource {
    fun fetchBooks(): List<BookDb>

    fun saveBooks(books: List<BookData>)

    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BookDataToDbMapper,
    ) : BooksCacheDataSource {
        override fun fetchBooks(): List<BookDb> {
            realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(booksDb)
            }
        }

        override fun saveBooks(books: List<BookData>) {
            realmProvider.provide().use { realm ->
                realm.executeTransaction {
                    books.forEach { book ->
                        book.mapTo(mapper, BookDbWrapper(it))
                    }
                }
            }
        }

        private inner class BookDbWrapper(
            realm: Realm
        ) : DbWrapper.Base<BookDb>(realm) {
            override fun dbClass(): Class<BookDb> {
                return BookDb::class.java
            }
        }
    }
}