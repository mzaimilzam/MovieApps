package id.co.zaimilzam.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.co.zaimilzam.core.data.source.local.entity.MovieEntity
import id.co.zaimilzam.core.data.source.local.entity.TvShowsEntity

@Database(entities = [MovieEntity::class, TvShowsEntity::class],
    version = 1,
    exportSchema = false)
abstract class FilmDataBase : RoomDatabase() {

    abstract fun movieDao() : MovieDao


}