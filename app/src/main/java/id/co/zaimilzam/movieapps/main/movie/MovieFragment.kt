package id.co.zaimilzam.movieapps.main.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.co.zaimilzam.core.data.Resource
import id.co.zaimilzam.core.ui.MovieAdapter
import id.co.zaimilzam.movieapps.databinding.MovieFragmentBinding
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val tags = MovieFragment::class.java.simpleName

    companion object {
        const val MOVIE_ID = "movie"
    }


    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: MovieAdapter
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {

            buildRecycleView()

            viewModel.movie.observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> binding?.pgBarDetail?.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding?.pgBarDetail?.visibility = View.GONE
                            Log.d(tags, "dataMovieFragment : $data")
                            adapter.setData(data.data)
                        }
                        is Resource.Error -> {
                            binding?.pgBarDetail?.visibility = View.GONE
                            Log.e(tags, "error movie Fragment")
                        }
                    }
                }
            })

            viewModel.search.observe(viewLifecycleOwner, { data ->
                adapter.setData(data)
            })

            binding?.searchMovie?.doOnTextChanged { text, _, _, _ ->
                viewModel.searchQuery.value = text.toString()
            }

        }
    }

    private fun buildRecycleView() {
        adapter = MovieAdapter()
        binding?.rvMovie?.setHasFixedSize(true)
        binding?.rvMovie?.layoutManager = LinearLayoutManager(context)
        binding?.rvMovie?.adapter = adapter

        adapter.onItemClick = { data ->
            val mIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
            mIntent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
            mIntent.putExtra(DetailMovieActivity.EXTRA_ID, MOVIE_ID)
            startActivity(mIntent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}