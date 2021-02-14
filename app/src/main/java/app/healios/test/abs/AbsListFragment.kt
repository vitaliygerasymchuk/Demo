package app.healios.test.abs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import app.healios.test.R

abstract class AbsListFragment<A : RecyclerView.Adapter<out RecyclerView.ViewHolder>> :
    AbsDaggerFragment() {

    protected lateinit var adapter: A
    protected abstract fun initAdapter(): A

    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    private var progressBar: ProgressBar? = null

    protected open fun getListLayout(): Int {
        return R.layout.list
    }

    protected open fun initLayoutManger(): RecyclerView.LayoutManager {
        return LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    protected open fun initItemDecoration(): RecyclerView.ItemDecoration? {
        return DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(getListLayout(), container, false)
        adapter = initAdapter()
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        progressBar = view.findViewById(R.id.progress)
        recyclerView.layoutManager = initLayoutManger()
        recyclerView.adapter = adapter
        initItemDecoration()?.let {
            recyclerView.addItemDecoration(it)
        }
        swipeRefreshLayout?.setOnRefreshListener {
            onRefresh()
        }
        return view
    }

    protected open fun onRefresh() {
        setIsLoading(true)
    }

    protected fun setIsLoading(isLoading: Boolean) {
        progressBar?.isVisible = isLoading
        if (swipeRefreshLayout?.isRefreshing == true) {
            swipeRefreshLayout?.isRefreshing = isLoading
        }
    }
}