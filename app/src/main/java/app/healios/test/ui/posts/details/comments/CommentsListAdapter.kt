package app.healios.test.ui.posts.details.comments

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.abs.AbsAdvancedListAdapter
import app.healios.test.data.model.Comment
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentsListAdapter : AbsAdvancedListAdapter<Comment>() {

    override fun getViewHolderLayout(viewType: Int): Int {
        return if (viewType != VT_LOADING) {
            R.layout.item_comment
        } else {
            super.getViewHolderLayout(viewType)
        }
    }

    override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
        if (viewType != VT_LOADING) {
            return CommentViewHolder(view)
        }
        return super.getViewHolder(view, viewType)
    }

    override fun onBind(holder: RecyclerView.ViewHolder, item: Comment) {
        if (holder is CommentViewHolder) {
            holder.bind(item)
        }
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(it: Comment) {
            itemView.name.text = it.name
            itemView.comment_body.text = it.body
            itemView.email.text = it.email
        }
    }
}