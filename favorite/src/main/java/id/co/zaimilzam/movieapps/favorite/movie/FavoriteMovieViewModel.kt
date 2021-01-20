package id.co.zaimilzam.movieapps.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.zaimilzam.core.domain.usecase.MovieUseCase

class FavoriteMovieViewModel (movieUseCase: MovieUseCase) : ViewModel() {

    val favorite = movieUseCase.getFavoriteMovie().asLiveData()
}