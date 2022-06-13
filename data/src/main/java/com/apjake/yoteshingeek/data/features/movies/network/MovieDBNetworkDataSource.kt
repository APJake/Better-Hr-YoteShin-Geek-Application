package com.apjake.yoteshingeek.data.features.movies.network

import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieDetailDto
import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieDto
import java.lang.Exception
import javax.inject.Inject

class MovieDBNetworkDataSource @Inject constructor(
    private val api: MovieDBApi
) {
    suspend fun getTopRatedMovies(): List<MovieDto> {
        val movies = api.getTopRatedMovies(
            MovieDBApi.API_KEY
        ).movieList
        if(movies.isNullOrEmpty()) throw Exception("Empty movie list")
        return movies
    }
    suspend fun getNowPlayingMovies(): List<MovieDto> {
        val movies = api.getNowPlayingMovies(
            MovieDBApi.API_KEY
        ).movieList
        if(movies.isNullOrEmpty()) throw Exception("Empty movie list")
        return movies
    }
    suspend fun getPopularMovies(): List<MovieDto> {
        val movies = api.getPopularMovies(
            MovieDBApi.API_KEY
        ).movieList
        if(movies.isNullOrEmpty()) throw Exception("Empty movie list")
        return movies
    }
    suspend fun getMovieDetail(id: Int): MovieDetailDto {
        return api.getMovieDetail(
            id,
            MovieDBApi.API_KEY,
        )
    }
}