package id.co.zaimilzam.core.domain.usecase

import id.co.zaimilzam.core.data.Resource
import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import kotlinx.coroutines.flow.Flow

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getAllTvShows(): Flow<Resource<List<TvShows>>>

    fun setFavoriteMovie(film: Movie, state : Boolean)

    fun getFavoriteMovie(): Flow<List<Movie>>

    fun setFavoriteTv(film: TvShows, state : Boolean)

    fun getFavoritetvShow(): Flow<List<TvShows>>

    fun getSeacrhMovie(name : String) : Flow<List<Movie>>

    fun getSeacrhTvShows(name : String) : Flow<List<TvShows>>
}