package app.healios.test.ui.posts.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.ui.abs.AbsSimpleListAdapter
import app.healios.test.ui.abs.SimpleItemClickListener
import app.healios.test.data.model.Post
import app.healios.test.databinding.ItemPostBinding

class PostListAdapter(private val listener: SimpleItemClickListener<Post>) :
    AbsSimpleListAdapter<Post, PostListAdapter.PostViewHolder>() {
    override fun getViewHolderLayout(viewType: Int): Int {
        return R.layout.item_post
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBind(holder: PostViewHolder, item: Post) {
        holder.bind(item)
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                listener.onItemClicked(get(adapterPosition), adapterPosition)
            }
        }

        fun bind(it: Post) {
            binding.post.title.text = it.title
            binding.post.body.text = it.body
        }
    }
}