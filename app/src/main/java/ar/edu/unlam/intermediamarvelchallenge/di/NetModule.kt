package ar.edu.unlam.intermediamarvelchallenge.di

import ar.edu.unlam.intermediamarvelchallenge.BuildConfig
import ar.edu.unlam.intermediamarvelchallenge.data.services.CharacterService
import ar.edu.unlam.intermediamarvelchallenge.data.services.EventService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideHttpClient() }
    single { provideRetrofit(get()) }
    single { provideEventService(get()) }
    single { provideCharacterService(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

fun provideCharacterService(retrofit: Retrofit): CharacterService =
    retrofit.create(CharacterService::class.java)

fun provideEventService(retrofit: Retrofit): EventService =
    retrofit.create(EventService::class.java)