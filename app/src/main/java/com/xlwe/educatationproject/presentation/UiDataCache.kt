package com.xlwe.educatationproject.presentation

interface UiDataCache {
    fun cache(list: List<BookUi>): BooksUi
    fun changeState(id: Int): List<BookUi>
    fun saveState()

    class Base(
        private val idCache: IdCache
    ) : UiDataCache {
        private val cachedList = mutableListOf<BookUi>()

        override fun cache(list: List<BookUi>): BooksUi {
            cachedList.clear()
            cachedList.addAll(list)
            val ids = idCache.read()
            var newList: List<BookUi> = ArrayList<BookUi>(list)
            ids.forEach { id ->
                newList = changeState(id)
            }
            return BooksUi.Base(newList)
        }

        override fun changeState(id: Int): List<BookUi> {
            val newList = mutableListOf<BookUi>()

            val item = cachedList.find { it.matches(id) }

            var add = false
            cachedList.forEachIndexed { index, book ->
                if (book is BookUi.Testament) {
                    if (item == book) {
                        val element = book.changeState()
                        cachedList[index] = element
                        newList.add(element)
                        add = !element.isCollapsed()
                    } else {
                        newList.add(book)
                        add = !book.isCollapsed()
                    }
                } else {
                    if (add) newList.add(book)
                }
            }

            return newList
        }

        override fun saveState() {
            idCache.start()
            cachedList.filter { it.isCollapsed() }.forEach {
                it.saveId(idCache)
            }

            idCache.finish()
        }

    }
}