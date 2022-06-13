package com.apjake.yoteshingeek.domain.usecase

import com.apjake.yoteshingeek.domain.repository.MovieDBRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieDBRepository
) {
    operator fun invoke() = repository.getTopRatedMovies()
}