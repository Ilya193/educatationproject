/*
package com.xlwe.educatationproject.data

import com.xlwe.educatationproject.data.books.BookDb
import com.xlwe.educatationproject.data.books.BooksCacheDataSource
import com.xlwe.educatationproject.data.books.BooksCacheMapper
import com.xlwe.educatationproject.data.books.BookCloud
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.io.IOException
import java.net.UnknownHostException

class BooksRepositoryTest : BaseBooksRepositoryTest() {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val expected = BooksData.Fail(unknownHostException)
        val actual = repository.fetchBooks()

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val testCacheDataSource = TestBooksCacheDataSource(false)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected =
            BooksData.Success(
                listOf(Book(0, "name0"), Book(1, "name1"), Book(2, "name2"))
            )

        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(false)
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(Book(10, "name10"), Book(11, "name11"), Book(12, "name12"))
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestBooksCloudDataSource(true)
        val testCacheDataSource = TestBooksCacheDataSource(true)
        val repository = BooksRepository.Base(
            testCloudDataSource,
            testCacheDataSource,
            BooksCloudMapper.Base(TestBookCloudMapper()),
            BooksCacheMapper.Base(TestBookCacheMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            listOf(Book(10, "name10"), Book(11, "name11"), Book(12, "name12"))
        )

        assertEquals(expected, actual)
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean,
        private val errorTypeNoConnection: Boolean = true
    ) :
        BooksCloudDataSource {
        override suspend fun fetchBooks(): List<BookCloud> {
            if (returnSuccess) {
                return listOf(
                    BookCloud(0, "name0"),
                    BookCloud(1, "name1"),
                    BookCloud(2, "name2")
                )
            } else {
                if (errorTypeNoConnection)
                    throw unknownHostException
                else
                    throw IOException()
            }
        }
    }

    private inner class TestBooksCacheDataSource(private val returnSuccess: Boolean) :
        BooksCacheDataSource {

        override fun fetchBooks(): List<BookDb> {
            return if (returnSuccess) {
                listOf(
                    BookDb().apply {
                        id = 10
                        name = "name10"
                    },
                    BookDb().apply {
                        id = 11
                        name = "name11"
                    },
                    BookDb().apply {
                        id = 12
                        name = "name12"
                    })
            } else {
                emptyList()
            }
        }

        override fun saveBooks(books: List<Book>) {

        }

    }
}*/
