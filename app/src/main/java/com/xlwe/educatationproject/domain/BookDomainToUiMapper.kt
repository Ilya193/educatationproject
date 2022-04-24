package com.xlwe.educatationproject.domain

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.presentation.BookUi

interface BookDomainToUiMapper : Abstract.Mapper {
    fun map(id: Int, name: String): BookUi
}