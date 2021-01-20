package id.co.zaimilzam.core.utils

import id.co.zaimilzam.core.data.source.local.entity.TvShowsEntity
import id.co.zaimilzam.core.data.source.remote.response.TvShowResponse
import id.co.zaimilzam.core.domain.model.TvShows

object DataMapperTvShow {
    fun mapResponsesToEntities(input: List<TvShowResponse>): List<TvShowsEntity> {
        val tourismList = ArrayList<TvShowsEntity>()
        input.map {
            val tourism = TvShowsEntity(
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

    fun mapEntitiesToDomain(input: List<TvShowsEntity>): List<TvShows> =
        input.map {
            TvShows(
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

    fun mapDomainToEntity(input: TvShows) = TvShowsEntity(
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