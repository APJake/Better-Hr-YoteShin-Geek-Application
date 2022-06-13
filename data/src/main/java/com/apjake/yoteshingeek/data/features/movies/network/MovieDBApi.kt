package com.apjake.yoteshingeek.data.features.movies.network

import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieDetailDto
import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApi {

    companion object{
        const val API_KEY = "c1344f49636138e12c007ee2cf3e7393"
        const val BASE_URL = "https://api.themoviedb.org/"
    }

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") api: String
    ): MovieResponseDto


    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") api: String
    ): MovieResponseDto

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") api: String
    ): MovieResponseDto

    @GET("/3/movie/{id}")
    suspend fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") api: String,
    ): MovieDetailDto

}