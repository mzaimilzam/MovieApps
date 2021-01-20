package id.co.zaimilzam.movieapps.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.zaimilzam.core.domain.usecase.MovieInteractor
import id.co.zaimilzam.core.domain.usecase.MovieUseCase

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideTourismUseCase(tourismInteractor: MovieInteractor): MovieUseCase

}