package com.apjake.yoteshingeek.data.features.movies.network.dto


import com.google.gson.annotations.SerializedName

data class MovieResponseDto(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val movieList: List<MovieDto>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
)