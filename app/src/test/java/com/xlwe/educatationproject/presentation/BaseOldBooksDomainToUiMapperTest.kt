package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.R
import com.xlwe.educatationproject.domain.BookDomainToUiMapper
import com.xlwe.educatationproject.domain.ErrorType
import org.junit.Assert.*
import org.junit.Test
import java.lang.IllegalStateException

class BaseOldBooksDomainToUiMapperTest {
    @Test
    fun test_fail() {
        val resourceProvider = TestResourceProvider()
        val mapper = BaseBooksDomainToUiMapper(resourceProvider, object : BookDomainToUiMapper {
            override fun map(id: Int, name: String): BookUi {
                throw IllegalStateException("not used here")
            }
        })

        var actual = mapper.map(ErrorType.NO_CONNECTION)
        var expected = BooksUi.Base(listOf(BookUi.Fail("no_connection_message")))

        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.SERVICE_UNAVAILABLE)
        expected = BooksUi.Base(listOf(BookUi.Fail("service_unavailable_message")))

        assertEquals(expected, actual)

        actual = mapper.map(ErrorType.GENERIC_ERROR)
        expected = BooksUi.Base(listOf(BookUi.Fail("something_went_wrong")))

        assertEquals(expected, actual)
    }

    private inner class TestResourceProvider : ResourceProvider {
        override fun getString(id: Int): String {
            return when (id) {
                R.string.no_connection_message -> "no_connection_message"
                R.string.service_unavailable_message -> "service_unavailable_message"
                R.string.something_went_wrong -> "something_went_wrong"
                else -> "else"
            }
        }

    }
}