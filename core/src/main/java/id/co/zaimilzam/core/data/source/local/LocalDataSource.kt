package id.co.zaimilzam.core.data.source.local

import id.co.zaimilzam.core.data.source.local.entity.MovieEntity
import id.co.zaimilzam.core.data.source.local.entity.TvShowsEntity
import id.co.zaimilzam.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
@Singleton
class LocalDataSource @Inject constructor(private val mMovieDao: MovieDao){

    fun getMovie():Flow<List<MovieEntity>> {
        return mMovieDao.getMovie()
    }

    fun getTvShow(): Flow<List<TvShowsEntity>> = mMovieDao.getTvShow()

    fun setFavoriteMovie(film: MovieEntity, newState: Boolean) {
        film.isFavorite = newState
        mMovieDao.updateMovie(film)
    }

    fun getFavoriteMovie(): Flow<List<MovieEntity>> = mMovieDao.getFavoriteMovie()

    fun setFavoriteTvShow(film: TvShowsEntity, newState: Boolean) {
        film.isFavorite = newState
        mMovieDao.updateTvShow(film)
    }

    fun getFavoriteTvShow():Flow<List<TvShowsEntity>> = mMovieDao.getFavoriteTvShow()

    suspend fun insetMovie(movie: List<MovieEntity>) {
        mMovieDao.insertMovie(movie)
    }

    suspend fun insertTvShow(tvShow: List<TvShowsEntity>) {
        mMovieDao.insertTvShow(tvShow)
    }

    fun getSearchMovie(search: String): Flow<List<MovieEntity>> =
        mMovieDao.getSearchMovie(search)

    fun getSearchTv(search: String): Flow<List<TvShowsEntity>> =
        mMovieDao.getSearchTv(search)
}