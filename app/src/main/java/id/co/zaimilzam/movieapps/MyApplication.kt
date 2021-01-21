package id.co.zaimilzam.movieapps

import android.app.Application
import id.co.zaimilzam.core.di.dataBaseModule
import id.co.zaimilzam.core.di.netWorkModule
import id.co.zaimilzam.core.di.repositoryModule
import id.co.zaimilzam.movieapps.di.usecaseModule
import id.co.zaimilzam.movieapps.di.viewModelModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by Muhammad Zaim Milzam on 10/01/21.
 * linkedin : Muhammad Zaim Milzam
 */

@ExperimentalCoroutinesApi
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    dataBaseModule,
                    netWorkModule,
                    repositoryModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}