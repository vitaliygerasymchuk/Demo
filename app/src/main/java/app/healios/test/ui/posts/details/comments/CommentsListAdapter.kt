package app.healios.test.ui.posts.details.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.data.model.Comment
import app.healios.test.databinding.ItemCommentBinding
import app.healios.test.ui.abs.AbsAdvancedListAdapter

class CommentsListAdapter : AbsAdvancedListAdapter<Comment>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType != VT_LOADING) {
            return CommentViewHolder(
                ItemCommentBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ),
                    parent,
                    false
                )
            )
        }
        return super.onCreateViewHolder(parent, viewType)
    }

    override fun onBind(holder: RecyclerView.ViewHolder, item: Comment) {
        if (holder is CommentViewHolder) {
            holder.bind(item)
        }
    }

    inner class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(it: Comment) {
            binding.name.text = it.name
            binding.commentBody.text = it.body
            binding.email.text = it.email
        }
    }
}