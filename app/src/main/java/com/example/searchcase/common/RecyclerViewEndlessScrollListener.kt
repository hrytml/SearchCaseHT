package com.example.searchcase.common

import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewEndlessScrollListener(private val gridLayoutManager: GridLayoutManager) :

    RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold = 4
    private var currentItemCount: Int = 0
    private var currentPage = 1
    @Nullable
    private var pageCount: Int? = null

    private val isTotalItemCountRecentlyIncreased: Boolean
        get() = currentItemCount > previousTotal + visibleThreshold

    private val isLastPage: Boolean
        get() = pageCount != null && currentPage == pageCount

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        currentItemCount = gridLayoutManager.itemCount

        if (currentItemCount < previousTotal) {
            reset()
        }

        if (loading && isTotalItemCountRecentlyIncreased) {
            loading = false
            previousTotal = currentItemCount
        }

        if (shouldLoadNextPage(visibleThreshold)) {
            currentPage++
            recyclerView.post { onLoadNextPage(currentPage) }
            loading = true
        }
    }

    private fun shouldLoadNextPage(threshold: Int): Boolean {
        return !loading && isLastVisibleItemPositionExceedsTotalItemCount(threshold) && !isLastPage
    }

    private fun isLastVisibleItemPositionExceedsTotalItemCount(threshold: Int): Boolean {
        return gridLayoutManager.findLastVisibleItemPosition() + threshold >= currentItemCount
    }

    private fun reset() {
        previousTotal = 0
        loading = true
        currentPage = 1
    }

    abstract fun onLoadNextPage(page: Int)

    fun setPageCount(pageCount: Int) {
        this.pageCount = pageCount
    }

}

