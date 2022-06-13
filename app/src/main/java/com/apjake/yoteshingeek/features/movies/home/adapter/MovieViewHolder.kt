package com.apjake.yoteshingeek.features.movies.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.apjake.yoteshingeek.R
import com.apjake.yoteshingeek.common.util.AppConstants
import com.apjake.yoteshingeek.databinding.SingleMovieItemBinding
import com.apjake.yoteshingeek.domain.model.Movie
import com.apjake.yoteshingeek.util.show
import com.bumptech.glide.Glide

class MovieViewHolder(
    val binding: SingleMovieItemBinding
): RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Movie){
        binding.ivMoviePoster.show(AppConstants.MOVIE_POSTER_PREFIX_THUMBNAIL + item.posterPath)
        binding.tvMovieTitle.text = item.title
    }
}