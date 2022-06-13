package com.apjake.yoteshingeek.di

import com.apjake.yoteshingeek.data.features.movies.MovieDBRepositoryImpl
import com.apjake.yoteshingeek.data.features.movies.network.MovieDBApi
import com.apjake.yoteshingeek.data.features.movies.network.MovieDBNetworkDataSource
import com.apjake.yoteshingeek.data.features.movies.network.MovieDetailMapper
import com.apjake.yoteshingeek.data.features.movies.network.MovieMapper
import com.apjake.yoteshingeek.domain.repository.MovieDBRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMovieDBRepository(
        networkDataSource: MovieDBNetworkDataSource,
        movieMapper: MovieMapper,
        movieDetailMapper: MovieDetailMapper
    ): MovieDBRepository{
        return MovieDBRepositoryImpl(
            networkDataSource,
            movieMapper,
            movieDetailMapper
        )
    }

    @Provides
    @Singleton
    fun provideMovieDBApi(): MovieDBApi{
        return Retrofit.Builder()
            .baseUrl(MovieDBApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieMapper() = MovieMapper()

    @Provides
    @Singleton
    fun provideMovieDetailMapper() = MovieDetailMapper()
}