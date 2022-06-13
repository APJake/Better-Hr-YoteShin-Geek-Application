package com.apjake.yoteshingeek.domain.usecase

import com.apjake.yoteshingeek.domain.repository.MovieDBRepository
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    private val repository: MovieDBRepository
) {
    operator fun invoke() = repository.getPopularMovies()
}