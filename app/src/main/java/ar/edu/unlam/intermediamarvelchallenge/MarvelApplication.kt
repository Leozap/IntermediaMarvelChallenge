package ar.edu.unlam.intermediamarvelchallenge

import android.app.Application
import ar.edu.unlam.intermediamarvelchallenge.di.businessModule
import ar.edu.unlam.intermediamarvelchallenge.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(listOf(networkModule, businessModule))
        }
    }
}