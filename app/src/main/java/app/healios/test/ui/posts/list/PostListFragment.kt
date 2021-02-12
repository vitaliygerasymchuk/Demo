package app.healios.test.ui.posts.list

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.healios.test.R
import app.healios.test.abs.AbsListFragment
import app.healios.test.abs.SimpleItemClickListener
import app.healios.test.data.api.Resource
import app.healios.test.data.model.Post

class PostListFragment : AbsListFragment<PostListAdapter>() {

    private val viewModel by viewModels<PostListViewModel>()

    override fun getLayout(): Int {
        return R.layout.list
    }

    override fun initItemDecoration(): RecyclerView.ItemDecoration? {
        return null
    }

    override fun initLayoutManger(): RecyclerView.LayoutManager {
        return if (requireContext().resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 2)
        } else {
            super.initLayoutManger()
        }
    }

    override fun initAdapter(): PostListAdapter {
        return PostListAdapter(object : SimpleItemClickListener<Post> {
            override fun onItemClicked(t: Post, adapterPosition: Int) {
                findNavController().navigate(
                    PostListFragmentDirections.actionPostListFragmentToPostDetailsFragment(
                        t
                    )
                )
            }
        })
    }

    override fun onInflated(view: View) {
        super.onInflated(view)
        viewModel.posts.observe(this, { it ->
            setIsLoading(it is Resource.Loading)
            it?.data?.let {
                adapter.refresh(it)
            }
        })
    }

    override fun onRefresh() {
        super.onRefresh()
        viewModel.refresh()
    }
}