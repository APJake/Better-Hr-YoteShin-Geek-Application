package com.apjake.yoteshingeek.features.movies.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.apjake.yoteshingeek.databinding.SingleMovieItemBinding
import com.apjake.yoteshingeek.domain.model.Movie

class MoviesAdapter(
    private val onItemClick: (Movie)->Unit
): ListAdapter<Movie, MovieViewHolder>(MovieItemDiffCallBack) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = SingleMovieItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return  MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.binding.root.setOnClickListener {
            onItemClick.invoke(movie)
        }
    }
}

object MovieItemDiffCallBack: DiffUtil.ItemCallback<Movie>(){
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

}