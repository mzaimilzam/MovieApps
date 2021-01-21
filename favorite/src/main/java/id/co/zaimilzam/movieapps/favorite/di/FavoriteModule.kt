package id.co.zaimilzam.movieapps.favorite.di

import id.co.zaimilzam.movieapps.favorite.movie.FavoriteMovieViewModel
import id.co.zaimilzam.movieapps.favorite.tvshows.FavoriteTvShowsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Muhammad Zaim Milzam on 21/01/21.
 * linkedin : Muhammad Zaim Milzam
 */

val favoriteModul = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowsViewModel(get()) }
}