package id.co.zaimilzam.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListTvShowsResponse(

//    @field:SerializedName("error")
//    val error: Boolean,
//
//    @field:SerializedName("message")
//    val message: String,

    @field:SerializedName("results")
    val list: List<TvShowResponse>
)