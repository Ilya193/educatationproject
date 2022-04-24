package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.R
import com.xlwe.educatationproject.domain.BookDomainToUiMapper
import com.xlwe.educatationproject.domain.TestamentType

class BaseBookDomainToUiMapper(
    private val resourceProvider: ResourceProvider
): BookDomainToUiMapper {
    override fun map(id: Int, name: String): BookUi {
        return when {
            TestamentType.NEW.matches(id) -> BookUi.Testament(id, resourceProvider.getString(R.string.new_testament))
            TestamentType.OLD.matches(id) -> BookUi.Testament(id, resourceProvider.getString(R.string.old_testament))
            else -> BookUi.Base(id, name)
        }
    }
}