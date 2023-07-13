package com.ewide.test.daryhilmyiswara.helper

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.*

class DebounceQueryTextListener(
    lifecycle: Lifecycle,
    private val onDebounceQueryTextChange: (String?) -> Unit,
    private val onDebounceQueryTextSubmit: (String?) -> Unit = {}
) : SearchView.OnQueryTextListener {

    private var debouncePeriod: Long = 300

    private val coroutineScope = lifecycle.coroutineScope

    private var searchJob: Job? = null

    override fun onQueryTextSubmit(query: String?): Boolean {
        onDebounceQueryTextSubmit(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        searchJob?.cancel()
        if (newText != null) {
            searchJob = coroutineScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) { delay(debouncePeriod) }
                onDebounceQueryTextChange(newText)
            }
        }

        return false
    }
}