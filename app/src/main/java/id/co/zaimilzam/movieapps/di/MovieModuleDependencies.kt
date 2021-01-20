package id.co.zaimilzam.movieapps.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import id.co.zaimilzam.core.domain.usecase.MovieUseCase

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
@EntryPoint
@InstallIn(ApplicationComponent::class)
interface MovieModuleDependencies {

    fun movieUseCase(): MovieUseCase
}