package com.xlwe.educatationproject.core

interface ToDbMapper<T, M : Abstract.Mapper> {

    fun mapTo(mapper: M, db: DbWrapper): T
}