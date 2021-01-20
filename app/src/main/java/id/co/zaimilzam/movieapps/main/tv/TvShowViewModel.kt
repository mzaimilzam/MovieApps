package id.co.zaimilzam.movieapps.main.tv

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import id.co.zaimilzam.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

@ExperimentalCoroutinesApi
class TvShowViewModel @ViewModelInject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    val searchQuery = MutableStateFlow("")

    private val tvFlow = searchQuery.flatMapLatest {
        movieUseCase.getSeacrhTvShows(it)
    }

    val tvShows = movieUseCase.getAllTvShows().asLiveData()

    val search = tvFlow.asLiveData()
}