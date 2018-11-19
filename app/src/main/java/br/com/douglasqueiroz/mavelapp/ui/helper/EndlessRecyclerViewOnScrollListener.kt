package br.com.douglasqueiroz.mavelapp.ui.helper

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewOnScrollListener(layoutManager: LinearLayoutManager?) :
    RecyclerView.OnScrollListener() {

    private val STARTING_PAGE_INDEX = 0

    private var sVisibleThreshold = 2
    private var mCurrentPage = 0
    private var mPreviousTotalItemCount = 0
    private var mLoading = true
    private var mLayoutManager: RecyclerView.LayoutManager? = layoutManager

    private fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = mLayoutManager!!.itemCount

        if (mLayoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions = (mLayoutManager as StaggeredGridLayoutManager)
                .findLastVisibleItemPositions(null)
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)

        } else if (mLayoutManager is LinearLayoutManager) {
            lastVisibleItemPosition = (mLayoutManager as LinearLayoutManager)
                .findLastVisibleItemPosition()

        } else if (mLayoutManager is GridLayoutManager) {
            lastVisibleItemPosition = (mLayoutManager as GridLayoutManager).findLastVisibleItemPosition()
        }


        if (totalItemCount < mPreviousTotalItemCount) {
            mCurrentPage = STARTING_PAGE_INDEX
            mPreviousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                mLoading = true
            }
        }

        if (mLoading && totalItemCount > mPreviousTotalItemCount + 1) {
            mLoading = false
            mPreviousTotalItemCount = totalItemCount
        }


        if (!mLoading && lastVisibleItemPosition + sVisibleThreshold > totalItemCount) {
            mCurrentPage++
            onLoadMore(mCurrentPage, totalItemCount)
            mLoading = true
        }
    }

    abstract fun onLoadMore(page: Int, totalItemsCount: Int)
}