package id.co.zaimilzam.movieapps.main.tv

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.zaimilzam.core.data.Resource
import id.co.zaimilzam.core.ui.TvShowsAdapter
import id.co.zaimilzam.movieapps.databinding.TvShowFragmentBinding
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class TvShowFragment : Fragment() {

    private val tags = TvShowFragment::class.java.simpleName

    companion object {
        const val MOVIE_ID = "tvshows"
    }

    private var _binding: TvShowFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: TvShowViewModel by viewModels()
    private lateinit var adapter: TvShowsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TvShowFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            buildRecycleView()

            viewModel.tvShows.observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> binding?.pgBarDetail?.visibility = View.GONE
                        is Resource.Success -> {
                            binding?.pgBarDetail?.visibility = View.GONE
                            adapter.setData(data.data)
                        }
                        is Resource.Error -> {
                            Log.e(tags, "Error TvShowsData ")
                        }
                    }
                }
            })

            viewModel.search.observe(viewLifecycleOwner, { data ->
                adapter.setData(data)
            })
            binding?.searchTv?.doOnTextChanged { text, _, _, _ ->
                viewModel.searchQuery.value = text.toString()
            }

        }
    }

    private fun buildRecycleView() {
        binding?.apply {
            adapter = TvShowsAdapter()
            rvTvShows.layoutManager = LinearLayoutManager(context)
            rvTvShows.setHasFixedSize(true)
            rvTvShows.adapter = adapter

            adapter.onItemClick = { data ->
                val mIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
                mIntent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
                mIntent.putExtra(DetailMovieActivity.EXTRA_ID, MOVIE_ID)
                startActivity(mIntent)

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}