package id.co.zaimilzam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("popularity")
    var popularity: Float,

    @field:SerializedName("vote_count")
    var voteCount: Int,

    @field:SerializedName("poster_path")
    var posterPath: String,

    @field:SerializedName("title")
    var title: String,

    @field:SerializedName("vote_average")
    var voteAverage: Float,

    @field:SerializedName("overview")
    var description: String,

    @field:SerializedName("release_date")
    var releaseDate: String
)