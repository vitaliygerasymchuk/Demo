package app.healios.test.abs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.shared.Loggable

interface SimpleItemClickListener<T> {
    fun onItemClicked(t: T, adapterPosition: Int)
}

abstract class AbsSimpleListAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>(),
    Loggable {

    val items = arrayListOf<T>()

    @LayoutRes
    protected abstract fun getViewHolderLayout(viewType: Int): Int
    protected abstract fun getViewHolder(view: View, viewType: Int): VH
    protected abstract fun onBind(holder: VH, item: T)

    open fun refresh(it: List<T>) {
        items.clear()
        items.addAll(it)
        notifyDataSetChanged()
    }

    protected fun get(position: Int): T {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(getViewHolderLayout(viewType), parent, false)
        return getViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        if (items.isEmpty()) {
            log("onBindViewHolder: no items in list.")
            return
        }
        onBind(holder, get(position))
    }
}