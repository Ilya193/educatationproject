/*
package com.xlwe.educatationproject.data

import com.xlwe.educatationproject.data.books.BookDb
import com.xlwe.educatationproject.data.books.BooksCacheDataSource
import com.xlwe.educatationproject.data.books.BooksCacheMapper
import com.xlwe.educatationproject.data.books.BookCloud
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class BooksRepositorySaveBooksTest : BaseBooksRepositoryTest() {

    @Test
    fun test_save_books() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource()
        val testCacheDataSource = TestBooksCacheDataSource()
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actualCloud = repository.fetchBooks()
        val expectedCloud = BooksData.Success(
            listOf(
                Book(0, "name0"),
                Book(1, "name1")
            )
        )

        assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchBooks()
        val expectedCache = BooksData.Success(
            listOf(
                Book(0, "name0 db"),
                Book(1, "name1 db")
            )
        )

        assertEquals(expectedCache, actualCache)
    }

    private inner class TestBooksCloudDataSource :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            return listOf(
                BookCloud(0, "name0"),
                BookCloud(1, "name1")
            )
        }
    }

    private inner class TestBooksCacheDataSource : BooksCacheDataSource {

        private val list = mutableListOf<BookDb>()

        override fun fetchBooks(): List<BookDb> {
            return list
        }

        override fun saveBooks(books: List<Book>) {
            books.map { book ->
                list.add(BookDb().apply {
                    this.id = book.id
                    this.name = book.name + " db"
                })
            }
        }

    }
}*/
