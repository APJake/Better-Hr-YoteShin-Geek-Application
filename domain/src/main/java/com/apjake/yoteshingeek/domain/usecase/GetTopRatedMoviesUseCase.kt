package com.apjake.yoteshingeek.domain.usecase

import com.apjake.yoteshingeek.domain.repository.MovieDBRepository
import javax.inject.Inject

class GetTopRatedMoviesUseCase @Inject constructor(
    private val repository: MovieDBRepository
) {
    operator fun invoke() = repository.getPopularMovies()
}