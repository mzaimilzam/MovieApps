package id.co.zaimilzam.movieapps.favorite

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.co.zaimilzam.movieapps.di.MovieModuleDependencies
import id.co.zaimilzam.movieapps.favorite.movie.FavoriteMovieFragment
import id.co.zaimilzam.movieapps.favorite.tvshows.FavoriteTvShowsFragment

@Component(dependencies = [MovieModuleDependencies::class])
interface FavoriteComponent {

    fun injectTvShow(fragment: FavoriteTvShowsFragment)
    fun injectMovie(fragment: FavoriteMovieFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: MovieModuleDependencies): Builder
        fun build(): FavoriteComponent
    }

}