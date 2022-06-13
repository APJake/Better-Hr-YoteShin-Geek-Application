package com.apjake.yoteshingeek.features.movies.movie_detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.apjake.yoteshingeek.R
import com.apjake.yoteshingeek.YoteShinApplication
import com.apjake.yoteshingeek.common.util.AppConstants
import com.apjake.yoteshingeek.databinding.FragmentMovieDetailBinding
import com.apjake.yoteshingeek.domain.model.MovieDetail
import com.apjake.yoteshingeek.util.UiEvent
import com.apjake.yoteshingeek.util.show
import com.apjake.yoteshingeek.util.showBackground
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private var snackBar: Snackbar? = null

    private var movieId: Int = -1

    private val args: MovieDetailFragmentArgs by navArgs()

    @Inject
    lateinit var movieDetailViewModel: MovieDetailViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieId = args.movieId
        if(movieId!= -1){
            movieDetailViewModel.loadMovieDetail(movieId)
        }

        lifecycleScope.launchWhenStarted {
            movieDetailViewModel.state.collectLatest { state ->
                binding.pbLoading.isVisible = state.isLoading
                binding.llHolder.isVisible = !state.isLoading && state.movieDetail!=null
                state.movieDetail?.let {
                    showMovieDetail(it)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            movieDetailViewModel.event.collect { event ->
                when(event){
                    is UiEvent.ShowErrorSnackBar ->{
                        snackBar?.dismiss()
                        snackBar = Snackbar.make(binding.root, event.message, Snackbar.LENGTH_LONG)
                            .setAction(R.string.try_again){
                                movieDetailViewModel.loadMovieDetail(movieId)
                                snackBar?.dismiss()
                            }
                        snackBar?.show()
                    }
                }
            }
        }
    }

    private fun showMovieDetail(detail: MovieDetail) {
        binding.ivBackground.showBackground(AppConstants.MOVIE_POSTER_PREFIX + detail.posterPath)
        binding.tvMovieTitle.text = detail.title
        binding.tvDescription.text = detail.overview
        binding.ivMovieBackdrop.show(AppConstants.MOVIE_POSTER_PREFIX + detail.backdropPath)
        binding.tvCountries.text = detail.productionCountries.joinToString(", ") { it.name }
        binding.tvCompanies.text = detail.productionCompanies.joinToString(", ") { it.name }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as YoteShinApplication).appComponent.inject(this)
    }
}