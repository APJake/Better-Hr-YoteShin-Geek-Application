package com.apjake.yoteshingeek.data.features.movies.network

import com.apjake.yoteshingeek.common.base.UniMapper
import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieDto
import com.apjake.yoteshingeek.domain.model.Movie

class MovieMapper: UniMapper<MovieDto, Movie> {
    override fun map(data: MovieDto): Movie {
        return Movie(
            id = data.id?:-1,
            adult = data.adult?:false,
            backdropPath = data.backdropPath.orEmpty(),
            genreIds = data.genreIds.orEmpty(),
            originalLanguage = data.originalLanguage.orEmpty(),
            originalTitle = data.originalTitle.orEmpty(),
            overview = data.overview.orEmpty(),
            popularity = data.popularity?:0.0,
            posterPath = data.posterPath.orEmpty(),
            releaseDate = data.releaseDate.orEmpty(),
            title = data.title.orEmpty(),
            video = data.video?:false,
            voteAverage = data.voteAverage?:0.0,
            voteCount = data.voteCount?:0
        )
    }

}