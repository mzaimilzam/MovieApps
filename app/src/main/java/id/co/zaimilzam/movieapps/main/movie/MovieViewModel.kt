package id.co.zaimilzam.movieapps.main.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.zaimilzam.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class MovieViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val movieFlow = searchQuery.flatMapLatest {
        movieUseCase.getSeacrhMovie(it)
    }

    val movie = movieUseCase.getAllMovie().asLiveData()

    val search = movieFlow.asLiveData()
}