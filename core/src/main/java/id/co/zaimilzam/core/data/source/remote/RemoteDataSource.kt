package id.co.zaimilzam.core.data.source.remote

import android.util.Log
import id.co.zaimilzam.core.data.source.remote.network.ApiResponse
import id.co.zaimilzam.core.data.source.remote.network.ApiService
import id.co.zaimilzam.core.data.source.remote.response.MovieResponse
import id.co.zaimilzam.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListMovie()
                val dataArray = response.list
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.list))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShows(): Flow<ApiResponse<List<TvShowResponse>>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListTvShow()
                val dataArray = response.list
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.list))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}