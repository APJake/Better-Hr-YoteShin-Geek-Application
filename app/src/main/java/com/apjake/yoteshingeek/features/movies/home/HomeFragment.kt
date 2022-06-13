package com.apjake.yoteshingeek.features.movies.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.apjake.yoteshingeek.R
import com.apjake.yoteshingeek.YoteShinApplication
import com.apjake.yoteshingeek.databinding.FragmentHomeBinding
import com.apjake.yoteshingeek.domain.model.Movie
import com.apjake.yoteshingeek.features.movies.home.adapter.MoviesAdapter
import com.apjake.yoteshingeek.util.UiEvent
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var popularMovieAdapter: MoviesAdapter
    private lateinit var nowPlayingMovieAdapter: MoviesAdapter
    private lateinit var topRatedMovieAdapter: MoviesAdapter

    private var snackBar: Snackbar? = null

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        popularMovieAdapter = MoviesAdapter(){
            goToMovieDetail(it)
        }
        binding.rcyPopularMovies.adapter = popularMovieAdapter
        binding.rcyPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        nowPlayingMovieAdapter = MoviesAdapter(){
            goToMovieDetail(it)
        }
        binding.rcyNowPlayingMovies.adapter = nowPlayingMovieAdapter
        binding.rcyNowPlayingMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        topRatedMovieAdapter = MoviesAdapter(){
            goToMovieDetail(it)
        }
        binding.rcyTopRatedMovies.adapter = topRatedMovieAdapter
        binding.rcyTopRatedMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        lifecycleScope.launchWhenStarted {
            homeViewModel.state.collect { state ->
                binding.pbLoadingNowPlaying.isVisible = state.nowPlayingLoading
                nowPlayingMovieAdapter.submitList(state.nowPlayingMovies)

                binding.pbLoadingPopularMovies.isVisible = state.popularLoading
                popularMovieAdapter.submitList(state.popularMovies)

                binding.pbLoadingTopRatedMovies.isVisible = state.topRatedLoading
                topRatedMovieAdapter.submitList(state.topRatedMovies)
            }
        }
        lifecycleScope.launchWhenStarted {
            homeViewModel.event.collectLatest { event ->
                when(event){
                    is UiEvent.ShowErrorSnackBar ->{
                        snackBar?.dismiss()
                        snackBar = Snackbar.make(binding.root, event.message, Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.try_again){
                                homeViewModel.reload()
                                snackBar?.dismiss()
                            }
                        snackBar?.show()
                    }
                }
            }
        }

    }

    private fun goToMovieDetail(it: Movie) {
        val action = HomeFragmentDirections.movieDetail(
            movieId = it.id
        )
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().applicationContext as YoteShinApplication).appComponent.inject(this)
    }


}