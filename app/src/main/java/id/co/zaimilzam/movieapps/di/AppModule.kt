package id.co.zaimilzam.movieapps.di


import id.co.zaimilzam.core.domain.usecase.MovieInteractor
import id.co.zaimilzam.core.domain.usecase.MovieUseCase
import id.co.zaimilzam.movieapps.detail.DetailMovieViewModel
import id.co.zaimilzam.movieapps.main.movie.MovieViewModel
import id.co.zaimilzam.movieapps.main.tv.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
val usecaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}