package id.co.zaimilzam.movieapps.favorite.tvshows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.zaimilzam.core.domain.usecase.MovieUseCase

class FavoriteTvShowsViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val getFavorite = movieUseCase.getFavoritetvShow().asLiveData()
}