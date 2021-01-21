package id.co.zaimilzam.core.data

import id.co.zaimilzam.core.data.source.local.LocalDataSource
import id.co.zaimilzam.core.data.source.remote.RemoteDataSource
import id.co.zaimilzam.core.data.source.remote.network.ApiResponse
import id.co.zaimilzam.core.data.source.remote.response.MovieResponse
import id.co.zaimilzam.core.data.source.remote.response.TvShowResponse
import id.co.zaimilzam.core.domain.model.Movie
import id.co.zaimilzam.core.domain.model.TvShows
import id.co.zaimilzam.core.domain.repository.IMovieRepository
import id.co.zaimilzam.core.utils.AppExecutors
import id.co.zaimilzam.core.utils.DataMapperMovie
import id.co.zaimilzam.core.utils.DataMapperTvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */

class MovieRepository constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovie().map {
                    DataMapperMovie.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapperMovie.mapResponsesToEntities(data)
                localDataSource.insetMovie(movieList)
            }
        }.asFlow()

    override fun getAllTvShows(): Flow<Resource<List<TvShows>>> =
        object : NetworkBoundResource<List<TvShows>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShows>> {
                return localDataSource.getTvShow().map {
                    DataMapperTvShow.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShows>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapperTvShow.mapResponsesToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override fun setFavoriteMovie(film: Movie, state: Boolean) {
        val movienEtity = DataMapperMovie.mapDomainToEntity(film)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movienEtity, state) }
    }

    override fun getFavoriteMovie(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovie().map {
            DataMapperMovie.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteTv(film: TvShows, state: Boolean) {
        val tvShowsEtity = DataMapperTvShow.mapDomainToEntity(film)
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShowsEtity, state) }
    }

    override fun getFavoritetvShow(): Flow<List<TvShows>> {
        return localDataSource.getFavoriteTvShow().map {
            DataMapperTvShow.mapEntitiesToDomain(it)
        }

    }

    override fun getSeacrhMovie(name: String): Flow<List<Movie>> {
        return localDataSource.getSearchMovie(name).map {
            DataMapperMovie.mapEntitiesToDomain(it)
        }
    }

    override fun getSeacrhTvShows(name: String): Flow<List<TvShows>> {
        return localDataSource.getSearchTv(name).map {
            DataMapperTvShow.mapEntitiesToDomain(it)
        }
    }

}