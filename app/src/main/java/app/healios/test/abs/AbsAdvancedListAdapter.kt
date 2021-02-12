package app.healios.test.abs

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R


abstract class AbsAdvancedListAdapter<T> : AbsSimpleListAdapter<T, RecyclerView.ViewHolder>() {

    private var isLoading = false

    fun setIsLoading(isLoading: Boolean) {
        this.isLoading = isLoading && items.isEmpty()
        if (this.isLoading) {
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return if (isLoading) 1 else super.getItemCount()
    }

    override fun refresh(it: List<T>) {
        isLoading = false
        super.refresh(it)
    }

    override fun getItemViewType(position: Int): Int {
        return if (isLoading) VT_LOADING else super.getItemViewType(position)
    }

    override fun getViewHolderLayout(viewType: Int): Int {
        return if (viewType == VT_LOADING) {
            R.layout.item_loading
        } else {
            super.getItemViewType(viewType)
        }
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        return object : RecyclerView.ViewHolder(view) {}
    }

    companion object {
        const val VT_LOADING = 500
    }
}