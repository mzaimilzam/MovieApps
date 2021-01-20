package id.co.zaimilzam.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */
@Parcelize
data class Movie(
    var courseId: Int? = 0,
    var tittle: String? = "",
    var description: String? = "",
    var image: String? = "",
    var popularity: Float? = 0F,
    var voteAverage: Float? = 0F,
    var voteCount: Int? = 0,
    var releaseDate: String? = "",
    var isFavorite: Boolean? = false,
) : Parcelable
