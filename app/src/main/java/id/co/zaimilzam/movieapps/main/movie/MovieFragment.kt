package id.co.zaimilzam.movieapps.main.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.zaimilzam.core.data.Resource
import id.co.zaimilzam.core.ui.MovieAdapter
import id.co.zaimilzam.movieapps.databinding.MovieFragmentBinding
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MovieFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    private var _binding: MovieFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            val adapter = MovieAdapter()
            binding.rvMovie.setHasFixedSize(true)
            binding.rvMovie.layoutManager = LinearLayoutManager(context)
            binding.rvMovie.adapter = adapter

            adapter.onItemClick = { data ->
                val mIntent = Intent(requireActivity(), DetailMovieActivity::class.java)
                mIntent.putExtra(DetailMovieActivity.EXTRA_DATA, data)
                mIntent.putExtra(DetailMovieActivity.EXTRA_ID, MOVIE_ID)
                startActivity(mIntent)
            }

            viewModel.movie.observe(viewLifecycleOwner, { data ->
                if (data != null) {
                    when (data) {
                        is Resource.Loading -> binding.pgBarDetail.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.pgBarDetail.visibility = View.GONE
                            adapter.setData(data.data)
                        }
                        is Resource.Error -> {
                            binding.pgBarDetail.visibility = View.GONE
                            binding.errorMovie.root.visibility = View.VISIBLE
                        }
                    }
                }
            })

            viewModel.search.observe(viewLifecycleOwner, { data ->
                adapter.setData(data)
            })

            binding.searchMovie.doOnTextChanged { text, _, _, _ ->
                viewModel.searchQuery.value = text.toString()
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        viewModel.movie.removeObservers(viewLifecycleOwner)
    }

}