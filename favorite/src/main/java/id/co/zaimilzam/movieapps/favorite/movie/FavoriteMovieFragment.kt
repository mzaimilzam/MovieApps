package id.co.zaimilzam.movieapps.favorite.movie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.EntryPointAccessors
import id.co.zaimilzam.core.ui.MovieAdapter
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import id.co.zaimilzam.movieapps.di.MovieModuleDependencies
import id.co.zaimilzam.movieapps.favorite.DaggerFavoriteComponent
import id.co.zaimilzam.movieapps.favorite.ViewModelFactory
import id.co.zaimilzam.movieapps.favorite.databinding.FavoriteMovieFragmentBinding
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "movie"
    }

    @Inject
    lateinit var factory: ViewModelFactory

    private var _binding: FavoriteMovieFragmentBinding? = null
    private val binding get() = _binding

    private lateinit var adapter: MovieAdapter

    private val viewModel: FavoriteMovieViewModel by viewModels {
        factory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity(),
                    MovieModuleDependencies::class.java
                )
            )
            .build()
            .injectMovie(this)

    }

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
            if (data.isNullOrEmpty()) {
                Log.e("TAG", "data kosong")
            } else {
                adapter.setData(data)

            }
        })
    }


    private fun buildRecycleView() {
        adapter = MovieAdapter()
        binding?.apply {
            adapter = MovieAdapter()
            rvfavoriteMovie.setHasFixedSize(true)
            rvfavoriteMovie.layoutManager = LinearLayoutManager(context)
            rvfavoriteMovie.adapter = adapter

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