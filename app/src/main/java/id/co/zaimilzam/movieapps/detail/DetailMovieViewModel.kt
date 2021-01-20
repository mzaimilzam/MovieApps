package id.co.zaimilzam.movieapps.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import id.co.zaimilzam.core.domain.usecase.MovieUseCase

/**
 * Created by Muhammad Zaim Milzam on 11/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
class DetailMovieViewModel @ViewModelInject constructor(private val movieUseCase: MovieUseCase) : ViewModel()  {

    fun setFavoriteMovie (movie : Movie, newState : Boolean) = movieUseCase.setFavoriteMovie(movie,newState)

    fun setFavoriteTvShows (tvShows: TvShows, newState: Boolean) = movieUseCase.setFavoriteTv(tvShows, newState)
}