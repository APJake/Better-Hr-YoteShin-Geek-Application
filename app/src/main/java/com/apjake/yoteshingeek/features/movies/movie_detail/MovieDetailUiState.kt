package com.apjake.yoteshingeek.features.movies.movie_detail

import com.apjake.yoteshingeek.domain.model.MovieDetail

data class MovieDetailUiState(
    val isLoading: Boolean = false,
    val movieDetail: MovieDetail? = null
)