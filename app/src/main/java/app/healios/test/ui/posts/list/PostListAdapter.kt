package app.healios.test.ui.posts.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.abs.AbsSimpleListAdapter
import app.healios.test.abs.SimpleItemClickListener
import app.healios.test.data.model.Post
import kotlinx.android.synthetic.main.post.view.*

class PostListAdapter(private val listener: SimpleItemClickListener<Post>) :
    AbsSimpleListAdapter<Post, PostListAdapter.PostViewHolder>() {
    override fun getViewHolderLayout(viewType: Int): Int {
        return R.layout.item_post
    }

    override fun getViewHolder(view: View, viewType: Int): PostViewHolder {
        return PostViewHolder(view)
    }

    override fun onBind(holder: PostViewHolder, item: Post) {
        holder.bind(item)
    }

    inner class PostViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                listener.onItemClicked(get(adapterPosition), adapterPosition)
            }
        }

        fun bind(it: Post) {
            itemView.title.text = it.title
            itemView.body.text = it.body
        }
    }
}