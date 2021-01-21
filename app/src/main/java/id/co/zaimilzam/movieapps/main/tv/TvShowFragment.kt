package id.co.zaimilzam.movieapps.main.tv

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.zaimilzam.core.data.Resource
import id.co.zaimilzam.core.ui.TvShowsAdapter
import id.co.zaimilzam.movieapps.databinding.TvShowFragmentBinding
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel


@ExperimentalCoroutinesApi
class TvShowFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "tvshows"
    }

    private var _binding: TvShowFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TvShowViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TvShowsAdapter()
        binding.rvTvShows.layoutManager = LinearLayoutManager(context)
        binding.rvTvShows.setHasFixedSize(true)
        binding.rvTvShows.adapter = adapter

        adapter.onItemClick = { data ->
            val mIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
            mIntent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
            mIntent.putExtra(DetailMovieActivity.EXTRA_ID, MOVIE_ID)
            startActivity(mIntent)

        }

        viewModel.tvShows.observe(viewLifecycleOwner, { data ->
            if (data != null) {
                when (data) {
                    is Resource.Loading -> binding.pgBarDetail.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pgBarDetail.visibility = View.GONE
                        adapter.setData(data.data)
                    }
                    is Resource.Error -> {
                        binding.pgBarDetail.visibility = View.GONE
                        binding.errortv.root.visibility = View.GONE
                    }
                }

            }
        })

        viewModel.search.observe(viewLifecycleOwner, { data ->
            adapter.setData(data)
        })


        binding.searchTv.doOnTextChanged { text, _, _, _ ->
            viewModel.searchQuery.value = text.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.tvShows.removeObservers(viewLifecycleOwner)
    }
}