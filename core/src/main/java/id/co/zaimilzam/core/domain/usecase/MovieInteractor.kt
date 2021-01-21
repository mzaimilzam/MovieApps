package id.co.zaimilzam.core.domain.usecase

import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import id.co.zaimilzam.core.domain.repository.IMovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
class MovieInteractor constructor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getAllTvShows() = movieRepository.getAllTvShows()

    override fun setFavoriteMovie(film: Movie, state: Boolean) =
        movieRepository.setFavoriteMovie(film, state)

    override fun getFavoriteMovie() = movieRepository.getFavoriteMovie()

    override fun setFavoriteTv(film: TvShows, state: Boolean) =
        movieRepository.setFavoriteTv(film, state)

    override fun getFavoritetvShow() = movieRepository.getFavoritetvShow()

    override fun getSeacrhMovie(name: String): Flow<List<Movie>> =
        movieRepository.getSeacrhMovie(name)

    override fun getSeacrhTvShows(name: String): Flow<List<TvShows>> =
        movieRepository.getSeacrhTvShows(name)
}