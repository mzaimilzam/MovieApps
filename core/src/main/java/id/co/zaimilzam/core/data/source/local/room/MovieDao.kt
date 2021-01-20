package id.co.zaimilzam.core.data.source.local.room

import androidx.room.*
import id.co.zaimilzam.core.data.source.local.entity.MovieEntity
import id.co.zaimilzam.core.data.source.local.entity.TvShowsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movieentity")
    fun getMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentity  ")
    fun getTvShow(): Flow<List<TvShowsEntity>>

    @Query("SELECT * FROM movieentity where isFavorite = 1")
    fun getFavoriteMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentity where isFavorite = 1")
    fun getFavoriteTvShow(): Flow<List<TvShowsEntity>>

    @Transaction
    @Query("SELECT * FROM movieentity where courseId = :movieId")
    fun getDetailMovie(movieId: Int): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentity where courseId = :tvId")
    fun getDetailTvShow(tvId: Int): Flow<List<TvShowsEntity>>

    @Transaction
    @Query("SELECT * FROM movieentity where title LIKE '%'|| :search || '%'")
    fun getSearchMovie(search: String): Flow<List<MovieEntity>>

    @Transaction
    @Query("SELECT * FROM tvshowentity where title LIKE '%'|| :search || '%'")
    fun getSearchTv(search: String): Flow<List<TvShowsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowsEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowsEntity)
}