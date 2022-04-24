package com.xlwe.educatationproject.core

interface Matcher<T> {
    fun matches(arg: T): Boolean
}
