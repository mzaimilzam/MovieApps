package id.co.zaimilzam.core.di

import androidx.room.Room
import id.co.zaimilzam.core.data.MovieRepository
import id.co.zaimilzam.core.data.source.local.LocalDataSource
import id.co.zaimilzam.core.data.source.local.room.FilmDataBase
import id.co.zaimilzam.core.data.source.remote.RemoteDataSource
import id.co.zaimilzam.core.data.source.remote.network.ApiService
import id.co.zaimilzam.core.domain.repository.IMovieRepository
import id.co.zaimilzam.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Muhammad Zaim Milzam on 21/01/21.
 * linkedin : Muhammad Zaim Milzam
 */


val dataBaseModule = module {
    factory { get<FilmDataBase>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("mzaimilzam".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            FilmDataBase::class.java, "Film.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val netWorkModule = module {
    single {
        val hostname = "tourism-api.dicoding.dev"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }

}