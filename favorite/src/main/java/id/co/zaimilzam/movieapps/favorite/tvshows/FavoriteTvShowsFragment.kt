package id.co.zaimilzam.movieapps.favorite.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.zaimilzam.core.ui.TvShowsAdapter
import id.co.zaimilzam.movieapps.detail.DetailMovieActivity
import id.co.zaimilzam.movieapps.favorite.databinding.FavoriteTvShowsFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel


class FavoriteTvShowsFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "tv_show"
    }


    private var _binding: FavoriteTvShowsFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: FavoriteTvShowsViewModel by viewModel()
    private lateinit var adapter: TvShowsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FavoriteTvShowsFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            buildRecycleView()

            viewModel.getFavorite.observe(viewLifecycleOwner, { data ->
                if (data.isNotEmpty()) binding?.emptyFavTv?.root?.visibility =
                    View.GONE else binding?.emptyFavTv?.root?.visibility = View.VISIBLE
                adapter.setData(data)
            })
        }
    }

    private fun buildRecycleView() {
        binding?.apply {
            adapter = TvShowsAdapter()
            rvFavoriteTv.layoutManager = LinearLayoutManager(context)
            rvFavoriteTv.setHasFixedSize(true)
            rvFavoriteTv.adapter = adapter

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