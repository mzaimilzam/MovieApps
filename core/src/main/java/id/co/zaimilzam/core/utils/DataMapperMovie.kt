package id.co.zaimilzam.core.utils

import id.co.zaimilzam.core.data.source.local.entity.MovieEntity
import id.co.zaimilzam.core.data.source.remote.response.MovieResponse
import id.co.zaimilzam.core.domain.model.Movie

object DataMapperMovie {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val tourismList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                courseId = it.id,
                tittle = it.title,
                description = it.description,
                image = it.posterPath,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                isFavorite = false

            )
            tourismList.add(tourism)
        }
        return tourismList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                courseId = it.courseId,
                tittle = it.tittle,
                description = it.description,
                image = it.image,
                popularity = it.popularity,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                releaseDate = it.releaseDate,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        courseId = input.courseId,
        tittle = input.tittle,
        description = input.description,
        image = input.image,
        popularity = input.popularity,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        releaseDate = input.releaseDate,
        isFavorite = input.isFavorite

    )
}