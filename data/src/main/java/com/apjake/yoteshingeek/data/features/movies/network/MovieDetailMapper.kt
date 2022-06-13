package com.apjake.yoteshingeek.data.features.movies.network

import com.apjake.yoteshingeek.common.base.UniMapper
import com.apjake.yoteshingeek.data.features.movies.network.dto.MovieDetailDto
import com.apjake.yoteshingeek.domain.model.*

class MovieDetailMapper: UniMapper<MovieDetailDto, MovieDetail> {
    override fun map(data: MovieDetailDto): MovieDetail {
        return MovieDetail(
            id = data.id?: -1,
            adult = data.adult?: false,
             backdropPath = data.backdropPath.orEmpty(),
             belongsToCollection = data.belongsToCollection?: false,
             budget = data.budget?: 0,
             genres = data.genres.orEmpty()
                 .map { Genre(
                 it.id?:-1,
                     it.name.orEmpty()
                 )},
             homepage = data.homepage.orEmpty(),
             imdbId = data.imdbId.orEmpty(),
             originalLanguage = data.originalLanguage.orEmpty(),
             originalTitle = data.originalTitle.orEmpty(),
             overview = data.overview.orEmpty(),
             popularity = data.popularity?: 0.0,
             posterPath = data.posterPath.orEmpty(),
             productionCompanies = data.productionCompanies.orEmpty()
                 .map { ProductionCompany(
                     id = it.id?: -1,
                     logoPath = it.logoPath.orEmpty(),
                     name = it.name.orEmpty(),
                     originCountry = it.originCountry.orEmpty()
                 ) },
             productionCountries = data.productionCountries.orEmpty()
                 .map{ ProductionCountry(
                     iso31661 = it.iso31661.orEmpty(),
                     name = it.name.orEmpty(),
                 )},
             releaseDate = data.releaseDate.orEmpty(),
             revenue = data.revenue?: 0,
             runtime = data.runtime?: 0,
             spokenLanguages = data.spokenLanguages.orEmpty()
                 .map { SpokenLanguage(
                     englishName = it.englishName.orEmpty(),
                     iso6391 = it.iso6391.orEmpty(),
                     name = it.name.orEmpty()
                 ) },
             status = data.status.orEmpty(),
             tagline = data.tagline.orEmpty(),
             title = data.title.orEmpty(),
             video = data.video?: false,
             voteAverage = data.voteAverage?: 0.0,
             voteCount = data.voteCount?: 0,
        )
    }
}