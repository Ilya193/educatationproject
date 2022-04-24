/*
package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.BookDataToDomainMapper
import com.xlwe.educatationproject.presentation.BookUi
import com.xlwe.educatationproject.presentation.BooksUi
import org.junit.Assert.assertEquals
import org.junit.Test

class BooksDomainTest {

    @Test
    fun test_map() {
        val bookMapper = object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                return BookUi.Base(id, name)
            }
        }

        val domain = BooksDomain.Success(listOf(
            BookData(1, "genesis", "ot"),
            BookData(66, "Relevation", "nt")
        ), object : BookDataToDomainMapper {
            override fun map(id: Int, name: String): BookDomain {
                return BookDomain.Base(id, name)
            }
        })

        val actual = domain.map(object : BooksDomainToUiMapper {
            override fun map(books: List<BookDomain>): BooksUi {
                return BooksUi.Success(books, bookMapper)
            }

            override fun map(errorType: ErrorType): BooksUi {
                throw  IllegalStateException()
            }
        })

        val expected =
            BooksUi.Success(
                listOf(
                    BookDomain.Testament(TestamentType.OLD),
                    BookDomain.Base(1, "genesis"),
                    BookDomain.Testament(TestamentType.NEW,),
                    BookDomain.Base(66, "Relevation"),
                ), bookMapper
            )


        assertEquals(expected, actual)
    }
}*/
