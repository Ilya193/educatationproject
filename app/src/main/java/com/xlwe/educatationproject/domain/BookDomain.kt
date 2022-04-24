package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Matcher
import com.xlwe.educatationproject.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, BookDomainToUiMapper> {

    data class Base(
        private val id: Int,
        private val name: String
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi {
            return mapper.map(id, name)
        }
    }

    data class Testament(
        private val type: TestamentType
    ) : BookDomain() {
        override fun map(mapper: BookDomainToUiMapper): BookUi {
            return type.map(mapper)
        }
    }
}

enum class TestamentType(private val id: Int) : Abstract.Object<BookUi, BookDomainToUiMapper>,
    Matcher<Int> {
    OLD(Int.MIN_VALUE),
    NEW(Int.MAX_VALUE);

    override fun matches(arg: Int) = this.id == arg
    override fun map(mapper: BookDomainToUiMapper): BookUi {
        return mapper.map(id, name)
    }
}