package id.co.zaimilzam.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "tvshowentity")
@Parcelize
data class TvShowsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "courseId")
    var courseId: Int? = 0,

    @ColumnInfo(name = "title")
    var tittle: String? = "",

    @ColumnInfo(name = "description")
    var description: String? = "",

    @ColumnInfo(name = "image")
    var image: String? = "",

    @ColumnInfo(name = "popularity")
    var popularity : Float? = 0F,

    @ColumnInfo(name = "voteAverage")
    var voteAverage : Float? = 0F,

    @ColumnInfo(name = "voteCount")
    var voteCount : Int? = 0,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite : Boolean? = false
) : Parcelable