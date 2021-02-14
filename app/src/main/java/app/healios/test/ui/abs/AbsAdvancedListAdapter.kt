package app.healios.test.ui.abs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.databinding.ItemLoadingBinding

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VT_LOADING) {
            object : RecyclerView.ViewHolder(
                ItemLoadingBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ).root
            ) {}
        }else{
            super.createViewHolder(parent, viewType)
        }
    }

    companion object {
        const val VT_LOADING = 500
    }
}