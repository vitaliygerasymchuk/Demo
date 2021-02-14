package app.healios.test.ui.posts.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import app.healios.test.abs.AbsDaggerFragment
import app.healios.test.data.api.Resource
import app.healios.test.data.model.Comment
import app.healios.test.data.model.Post
import app.healios.test.data.model.User
import app.healios.test.databinding.FragmentPostDetailsBinding
import app.healios.test.shared.ARG_POST
import app.healios.test.ui.posts.details.comments.CommentsListAdapter

//For the simplicity i used RecyclerView nested into scroll view. This could be just a single recycler view
class PostDetailsFragment : AbsDaggerFragment() {

    private val binding: FragmentPostDetailsBinding get() = viewBinding as FragmentPostDetailsBinding

    private val viewModel by viewModels<PostDetailsViewModel>()
    private val commentsListAdapter = CommentsListAdapter()
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.post = requireArguments().getParcelable(ARG_POST)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentPostDetailsBinding.inflate(inflater, container, false)
        itemDecoration = DividerItemDecoration(
            requireContext(),
            LinearLayoutManager.VERTICAL
        )
        showPost(viewModel.post)
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = commentsListAdapter

        viewModel.getUser().observe(viewLifecycleOwner, {
            binding.progress.isVisible = false
            showUser(it)
        })
        viewModel.getComments().observe(viewLifecycleOwner, {
            showComments(it)
        })
        return binding.root
    }

    private fun showPost(post: Post?) {
        binding.post.title.text = post?.title.orEmpty()
        binding.post.body.text = post?.body.orEmpty()
    }

    private fun showUser(resource: Resource<User?>) {
        resource.data?.let {
            binding.fullName.text = it.name
            binding.username.text = it.userName
            binding.email.text = it.email
            binding.phone.text = it.phone
            binding.website.text = it.webSite
        }
    }

    private fun showComments(resource: Resource<List<Comment>>) {
        commentsListAdapter.setIsLoading(resource is Resource.Loading && resource.data.isNullOrEmpty())
        resource.data?.let {
            if (binding.recyclerView.itemDecorationCount == 0) {
                binding.recyclerView.addItemDecoration(
                    itemDecoration
                )
            }
            commentsListAdapter.refresh(it)
        }
    }
}