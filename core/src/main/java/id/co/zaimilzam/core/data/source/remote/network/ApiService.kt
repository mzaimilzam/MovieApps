package id.co.zaimilzam.core.data.source.remote.network

import id.co.zaimilzam.core.data.source.remote.response.ListMovieResponse
import id.co.zaimilzam.core.data.source.remote.response.ListTvShowsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("movie/popular?api_key=090ef4eef9fcf1858109a3af3c5f34c1&language=en-US&page=1")
    suspend fun getListMovie(): ListMovieResponse

    @GET("tv/popular?api_key=090ef4eef9fcf1858109a3af3c5f34c1&language=en-US&page=1")
    suspend fun getListTvShow(): ListTvShowsResponse
}
