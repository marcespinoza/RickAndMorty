package rick.and.morty.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import rick.and.morty.data.RickMortyApi
import rick.and.morty.data.repository.RickMortyRepository
import rick.and.morty.domain.repository.RickMortyRepositoryImpl
import rick.and.morty.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRickMortiApi():RickMortyApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RickMortyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMarvelRepository(api:RickMortyApi):RickMortyRepository{
        return RickMortyRepositoryImpl(api)
    }
}