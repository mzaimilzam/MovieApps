package id.co.zaimilzam.movieapps.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import id.co.zaimilzam.movieapps.R
import id.co.zaimilzam.movieapps.databinding.ActivityDetailFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_ID = "extra_id"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }

    private val idMovie = "movie"

    private var dataMovie: Movie? = null
    private var dataTvShows: TvShows? = null


    private val viewModel: DetailMovieViewModel by viewModel()
    private lateinit var binding: ActivityDetailFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataExtra = intent.extras
        val id = dataExtra?.getString(EXTRA_ID).toString()

        // check id movie or tvshows
        when (idMovie) {
            id -> {
                dataMovie = dataExtra?.getParcelable(EXTRA_DATA)
                setupDetailMovie(dataMovie)
            }
            else -> {
                dataTvShows = dataExtra?.getParcelable(EXTRA_DATA)
                setupDetailTvShows(dataTvShows)
            }
        }

    }

    private fun setupDetailMovie(data: Movie?) {

        val releaseDate = "Release : ${data?.releaseDate}"
        val image = "$IMAGE_URL${data?.image}"

        binding.tvDetailContentTittle.text = data?.tittle
        binding.tvDetailContentDescription.text = data?.description
        binding.tvRelease.text = releaseDate
        binding.tvRating.text = data?.voteAverage.toString()
        var state = data?.isFavorite ?: false
        setFavoriteState(state)

        binding.favorite.setOnClickListener {
            state = !state
            viewModel.setFavoriteMovie(dataMovie!!, state)
            setFavoriteState(state)
        }


        // set circular progress loading
        val circularProgressDrawable = CircularProgressDrawable(this@DetailMovieActivity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this@DetailMovieActivity)
            .load(image)
            .placeholder(circularProgressDrawable)
            .into(binding.imgDetail)


    }

    private fun setupDetailTvShows(data: TvShows?) {

        val releaseDate = "Release : ${data?.releaseDate}"
        val image = "$IMAGE_URL${data?.image}"

        binding.tvDetailContentTittle.text = data?.tittle
        binding.tvDetailContentDescription.text = data?.description
        binding.tvRelease.text = releaseDate
        binding.tvRating.text = data?.voteAverage.toString()
        var state = data?.isFavorite ?: false
        setFavoriteState(state)

        binding.favorite.setOnClickListener {
            state = !state
            dataTvShows?.let { it1 -> viewModel.setFavoriteTvShows(it1, state) }
            setFavoriteState(state)
        }


        // set circular progress loading
        val circularProgressDrawable = CircularProgressDrawable(this@DetailMovieActivity)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(this@DetailMovieActivity)
            .load(image)
            .placeholder(circularProgressDrawable)
            .into(binding.imgDetail)

    }

    private fun setFavoriteState(state: Boolean) {
        if (state) {
            binding.favorite.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
            )
        } else {
            binding.favorite.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

}