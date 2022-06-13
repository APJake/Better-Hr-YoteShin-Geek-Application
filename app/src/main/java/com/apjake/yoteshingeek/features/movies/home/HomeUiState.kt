package com.apjake.yoteshingeek.features.movies.home

import com.apjake.yoteshingeek.domain.model.Movie

data class HomeUiState(
    val popularLoading: Boolean = false,
    val popularMovies: List<Movie> = emptyList(),
    val topRatedLoading: Boolean = false,
    val topRatedMovies: List<Movie> = emptyList(),
    val nowPlayingLoading: Boolean = false,
    val nowPlayingMovies: List<Movie> = emptyList()
)