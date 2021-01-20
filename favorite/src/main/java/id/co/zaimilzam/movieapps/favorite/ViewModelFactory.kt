package id.co.zaimilzam.movieapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.co.zaimilzam.core.domain.usecase.MovieUseCase
import id.co.zaimilzam.movieapps.favorite.movie.FavoriteMovieViewModel
import id.co.zaimilzam.movieapps.favorite.tvshows.FavoriteTvShowsViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val movieUseCase: MovieUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(movieUseCase) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowsViewModel::class.java) -> {
                FavoriteTvShowsViewModel(movieUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}