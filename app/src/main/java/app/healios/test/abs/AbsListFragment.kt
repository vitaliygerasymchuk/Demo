package app.healios.test.abs

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list.*
import kotlinx.android.synthetic.main.list.view.*

abstract class AbsListFragment<A : RecyclerView.Adapter<out RecyclerView.ViewHolder>> :
    AbsDaggerFragment() {

    protected lateinit var adapter: A

    protected abstract fun initAdapter(): A

    protected open fun initLayoutManger(): RecyclerView.LayoutManager {
        return LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    protected open fun initItemDecoration(): RecyclerView.ItemDecoration? {
        return DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    }

    override fun onInflated(view: View) {
        adapter = initAdapter()
        view.recycler_view.layoutManager = initLayoutManger()
        view.recycler_view.adapter = adapter
        initItemDecoration()?.let {
            view.recycler_view.addItemDecoration(it)
        }
        view.swipe_refresh_layout.setOnRefreshListener {
            onRefresh()
        }
    }

    protected open fun onRefresh() {
        setIsLoading(true)
    }

    protected fun setIsLoading(isLoading: Boolean) {
        view?.progress?.isVisible = isLoading
        if (view?.swipe_refresh_layout?.isRefreshing == true) {
            view?.swipe_refresh_layout?.isRefreshing = isLoading
        }
    }
}