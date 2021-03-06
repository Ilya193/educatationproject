package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.data.books.BookData
import com.xlwe.educatationproject.data.books.BookDataToDomainMapper
import com.xlwe.educatationproject.data.books.BooksDataToDomainMapper
import com.xlwe.educatationproject.data.books.TestamentTemp
import retrofit2.HttpException
import java.net.UnknownHostException

class BaseBooksDataToDomainMapper(
    private val bookMapper: BookDataToDomainMapper,
) : BooksDataToDomainMapper {
    override fun map(books: List<BookData>): BooksDomain {
        val data = mutableListOf<BookDomain>()
        val temp = TestamentTemp.Base()
        books.forEach { bookData ->
            if (!bookData.matches(temp)) {
                if (temp.isEmpty())
                    data.add(BookDomain.Testament(TestamentType.OLD))
                else
                    data.add(BookDomain.Testament(TestamentType.NEW))
                bookData.saveTestament(temp)
            }
            data.add(bookData.map(bookMapper))
        }
        return BooksDomain.Success(data)
    }

    override fun map(e: Exception): BooksDomain {
        val errorType = when (e) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
        return BooksDomain.Fail(errorType)
    }

}