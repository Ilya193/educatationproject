package com.xlwe.educatationproject.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xlwe.educatationproject.domain.BooksDomainToUiMapper
import com.xlwe.educatationproject.domain.BooksInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication,
    private val uiDataCache: UiDataCache
) : ViewModel() {

    fun fetchBooks() {
        communication.map(listOf(BookUi.Progress))

        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = booksInteractor.fetchBooks()
            val resultUi = resultDomain.map(mapper)
            val actual = resultUi.cache(uiDataCache)
            withContext(Dispatchers.Main) {
                actual.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<BookUi>>) {
        communication.observe(owner, observer)
    }

    fun collapseOrExpand(id: Int) {
        communication.map(uiDataCache.changeState(id))
    }

    fun saveCollapsedStates() {
        uiDataCache.saveState()
    }
}