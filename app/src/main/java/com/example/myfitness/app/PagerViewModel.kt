package com.example.myfitness.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myfitness.Page

class PagerViewModel() : ViewModel() {
    private val _currentPage = mutableStateOf(Page.WORKOUTS)
    val currentPage: MutableState<Page> = _currentPage

    fun onChangePage(page: Page) {
        _currentPage.value = page
    }
}