package com.xlwe.educatationproject.presentation

import com.xlwe.educatationproject.core.Abstract
import com.xlwe.educatationproject.core.Matcher

sealed class BookUi : Abstract.Object<Unit, BookUi.StringMapper>, Matcher<Int>, Collapsing,
    Comparing {

    override fun map(mapper: StringMapper) = Unit

    override fun matches(arg: Int): Boolean = false

    open fun changeState(): BookUi = Progress

    open fun saveId(idCache: IdCache) = Unit

    object Progress : BookUi()
    object Empty : BookUi()

    abstract class Info(
        protected open val id: Int,
        protected open val name: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(name)
        }

        override fun matches(arg: Int): Boolean {
            return arg == id
        }
    }

    data class Base(
        override val id: Int,
        override val name: String
    ) : Info(id, name) {
        override fun sameContent(bookUi: BookUi): Boolean {
            return if (bookUi is Base) {
                name == bookUi.name
            } else
                false
        }

        override fun same(bookUi: BookUi): Boolean {
            return bookUi is Base && id == bookUi.id
        }
    }

    data class Testament(
        override val id: Int,
        override val name: String,
        private val collapsed: Boolean = false
    ) : Info(id, name) {
        override fun collapseOrExpand(listener: MainAdapter.CollapseListener) {
            listener.collapseOrExpand(id)
        }

        override fun showCollapsed(mapper: CollapseMapper) {
            mapper.show(collapsed)
        }

        override fun changeState(): BookUi {
            return Testament(id, name, !collapsed)
        }

        override fun isCollapsed(): Boolean {
            return collapsed
        }

        override fun sameContent(bookUi: BookUi): Boolean {
            return if (bookUi is Testament) {
                name == bookUi.name && collapsed == bookUi.collapsed
            } else
                false
        }

        override fun same(bookUi: BookUi): Boolean {
            return bookUi is Testament && id == bookUi.id
        }

        override fun saveId(idCache: IdCache) {
            idCache.save(id)
        }
    }

    data class Fail(
        private val message: String
    ) : BookUi() {
        override fun map(mapper: StringMapper) {
            mapper.map(message)
        }

        override fun sameContent(bookUi: BookUi): Boolean {
            return if (bookUi is Fail) {
                message == bookUi.message
            } else
                false
        }

        override fun same(bookUi: BookUi): Boolean {
            return sameContent(bookUi)
        }
    }

    interface StringMapper : Abstract.Mapper {
        fun map(text: String)
    }

    interface CollapseMapper : Abstract.Mapper {
        fun show(collapsed: Boolean)
    }
}

interface Collapsing {
    fun collapseOrExpand(listener: MainAdapter.CollapseListener) = Unit
    fun showCollapsed(mapper: BookUi.CollapseMapper) = Unit
    fun isCollapsed() = false
}

interface Comparing {
    fun same(bookUi: BookUi) = false
    fun sameContent(bookUi: BookUi) = false
}