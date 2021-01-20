package id.co.zaimilzam.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.co.zaimilzam.core.data.source.local.room.FilmDataBase
import id.co.zaimilzam.core.data.source.local.room.MovieDao
import javax.inject.Singleton

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */

@Module
@InstallIn(ApplicationComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): FilmDataBase = Room.databaseBuilder(
        context,
        FilmDataBase::class.java, "Film.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: FilmDataBase): MovieDao = database.movieDao()
}