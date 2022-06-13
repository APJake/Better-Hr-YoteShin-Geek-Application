package com.apjake.yoteshingeek.domain.repository

import com.apjake.yoteshingeek.common.util.Resource
import com.apjake.yoteshingeek.domain.model.Movie
import com.apjake.yoteshingeek.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MovieDBRepository {
    fun getTopRatedMovies(): Flow<Resource<List<Movie>>>
    fun getPopularMovies(): Flow<Resource<List<Movie>>>
    fun getNowPlayingMovies(): Flow<Resource<List<Movie>>>
    fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>>
}