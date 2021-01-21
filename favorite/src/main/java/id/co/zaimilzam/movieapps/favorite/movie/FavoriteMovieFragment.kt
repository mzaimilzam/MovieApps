package id.co.zaimilzam.movieapps.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.zaimilzam.core.ui.MovieAdapter
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import id.co.zaimilzam.movieapps.favorite.databinding.FavoriteMovieFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteMovieFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    private var _binding: FavoriteMovieFragmentBinding? = null
    private val binding get() = _binding

    private var adapter: MovieAdapter? = null

    private val viewModel: FavoriteMovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteMovieFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buildRecycleView()

        viewModel.favorite.observe(viewLifecycleOwner, { data ->
            adapter?.setData(data)
            binding?.emptyFavMovie?.root?.visibility =
                if (data.isNotEmpty()) View.GONE else View.VISIBLE
        })
    }


    private fun buildRecycleView() {
        adapter = MovieAdapter()
        binding?.apply {
            adapter = MovieAdapter()
            rvfavoriteMovie.setHasFixedSize(true)
            rvfavoriteMovie.layoutManager = LinearLayoutManager(context)
            rvfavoriteMovie.adapter = adapter

            adapter?.onItemClick = { data ->
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