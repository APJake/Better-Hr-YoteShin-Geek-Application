package com.apjake.yoteshingeek.data.features.movies

import com.apjake.yoteshingeek.common.util.Resource
import com.apjake.yoteshingeek.data.features.movies.network.MovieDBNetworkDataSource
import com.apjake.yoteshingeek.data.features.movies.network.MovieDetailMapper
import com.apjake.yoteshingeek.data.features.movies.network.MovieMapper
import com.apjake.yoteshingeek.data.features.movies.util.GetErrorMessage
import com.apjake.yoteshingeek.domain.model.Movie
import com.apjake.yoteshingeek.domain.model.MovieDetail
import com.apjake.yoteshingeek.domain.repository.MovieDBRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieDBRepositoryImpl @Inject constructor(
    private val networkDataSource: MovieDBNetworkDataSource,
    private val movieMapper: MovieMapper,
    private val movieDetailMapper: MovieDetailMapper
): MovieDBRepository {

    override fun getTopRatedMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val movies = networkDataSource.getTopRatedMovies()
                .map { movieMapper.map(it) }
            emit(Resource.Success(movies))
        }catch (e: HttpException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: IOException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: Exception){
            emit(Resource.Error(e.message?: "Error"))
        }
    }

    override fun getNowPlayingMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val movies = networkDataSource.getNowPlayingMovies()
                .map { movieMapper.map(it) }
            emit(Resource.Success(movies))
        }catch (e: HttpException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: IOException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: Exception){
            emit(Resource.Error(e.message?: "Error"))
        }
    }

    override fun getPopularMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())
        try {
            val movies = networkDataSource.getPopularMovies()
                .map { movieMapper.map(it) }
            emit(Resource.Success(movies))
        }catch (e: HttpException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: IOException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: Exception){
            emit(Resource.Error(e.message?: "Error"))
        }
    }

    override fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())
        try {
            val detail = networkDataSource.getMovieDetail(id)
            emit(Resource.Success(movieDetailMapper.map(detail)))
        }catch (e: HttpException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }catch (e: IOException){
            emit(Resource.Error(GetErrorMessage.fromException(e)))
        }
    }
}