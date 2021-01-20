package id.co.zaimilzam.movieapps.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import id.co.zaimilzam.movieapps.R
import id.co.zaimilzam.movieapps.databinding.ActivityDetailFavoriteBinding

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity(), View.OnClickListener {

    private val tag = DetailMovieActivity::class.java.simpleName

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_ID = "extra_id"
        const val IMAGE_URL = "https://image.tmdb.org/t/p/original"
    }

    private val idMovie = "movie"

    private var state: Boolean? = false

    private var dataMovie: Movie? = null
    private var dataTvShows: TvShows? = null

    private var id = ""

    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var binding: ActivityDetailFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataExtra = intent.extras
        id = dataExtra?.getString(EXTRA_ID).toString()

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

        binding.favorite.setOnClickListener(this)
    }

    private fun setupDetailMovie(data: Movie?) {
        binding.apply {
            val releaseDate = "Release : ${data?.releaseDate}"
            val image = "$IMAGE_URL${data?.image}"

            tvDetailContentTittle.text = data?.tittle
            tvDetailContentDescription.text = data?.description
            tvRelease.text = releaseDate
            tvRating.text = data?.voteAverage.toString()
            state = data?.isFavorite

            setFavoriteState(state)


            // set circular progress loading
            val circularProgressDrawable = CircularProgressDrawable(this@DetailMovieActivity)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(this@DetailMovieActivity)
                .load(image)
                .placeholder(circularProgressDrawable)
                .into(imgDetail)

        }
    }

    private fun setupDetailTvShows(data: TvShows?) {
        binding.apply {
            val releaseDate = "Release : ${data?.releaseDate}"
            val image = "$IMAGE_URL${data?.image}"

            tvDetailContentTittle.text = data?.tittle
            tvDetailContentDescription.text = data?.description
            tvRelease.text = releaseDate
            tvRating.text = data?.voteAverage.toString()
            state = data?.isFavorite
            setFavoriteState(state)

            // set circular progress loading
            val circularProgressDrawable = CircularProgressDrawable(this@DetailMovieActivity)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(this@DetailMovieActivity)
                .load(image)
                .placeholder(circularProgressDrawable)
                .into(imgDetail)

        }
    }

    private fun setFavoriteState(state: Boolean?) {
        if (state == true) {
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

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.favorite -> {
                val statusState = !state!!
                setFavoriteState(statusState)
                state = statusState
                Log.d(tag, "UpdateState : $statusState")
                if (idMovie == id) {
                    viewModel.setFavoriteMovie(dataMovie!!, statusState)
                } else {
                    state?.let { viewModel.setFavoriteTvShows(dataTvShows!!, statusState) }
                }
            }
        }
    }

}