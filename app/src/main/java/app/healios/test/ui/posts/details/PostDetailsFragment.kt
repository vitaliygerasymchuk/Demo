package app.healios.test.ui.posts.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.healios.test.R
import app.healios.test.abs.AbsDaggerFragment
import app.healios.test.data.api.Resource
import app.healios.test.shared.ARG_POST
import app.healios.test.ui.posts.details.comments.CommentsListAdapter
import kotlinx.android.synthetic.main.fragment_post_details.view.*
import kotlinx.android.synthetic.main.post.view.*

//For the simplicity i used RecyclerView nested into scroll view. This could be just single recycler view
class PostDetailsFragment : AbsDaggerFragment() {

    private val viewModel by viewModels<PostDetailsViewModel>()
    private val commentsListAdapter = CommentsListAdapter()
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.post = requireArguments().getParcelable(ARG_POST)!!
    }

    override fun getLayout(): Int {
        return R.layout.fragment_post_details
    }

    override fun onInflated(view: View) {
        view.title.text = viewModel.post?.title.orEmpty()
        view.body.text = viewModel.post?.body.orEmpty()
        itemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )

        //comments view
        view.recycler_view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        view.recycler_view.adapter = commentsListAdapter

        viewModel.getUser().observe(this, { it ->
            view.progress.isVisible = it is Resource.Loading
            if (it is Resource.Success) {
                it.data?.let {
                    view.full_name.text = it.name
                    view.username.text = it.userName
                    view.email.text = it.email
                    view.phone.text = it.phone
                    view.website.text = it.webSite
                }
            }
        })

        viewModel.getComments().observe(this, {
            commentsListAdapter.setIsLoading(it is Resource.Loading && it.data.isNullOrEmpty())
            if (it.data != null) {
                if (view.recycler_view.itemDecorationCount == 0) {
                    view.recycler_view.addItemDecoration(
                        itemDecoration
                    )
                }
                commentsListAdapter.refresh(it.data)
            }
        })
    }
}