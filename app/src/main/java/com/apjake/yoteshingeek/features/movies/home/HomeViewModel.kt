package com.apjake.yoteshingeek.features.movies.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apjake.yoteshingeek.common.util.Resource
import com.apjake.yoteshingeek.domain.usecase.GetNowPlayingMoviesUseCase
import com.apjake.yoteshingeek.domain.usecase.GetPopularMoviesUseCase
import com.apjake.yoteshingeek.domain.usecase.GetTopRatedMoviesUseCase
import com.apjake.yoteshingeek.util.UiEvent
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Error
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(HomeUiState())
    val state = _state.asStateFlow()

    private var _event = MutableSharedFlow<UiEvent>()
    val event = _event.asSharedFlow()

    init {
        reload()
    }

    fun reload(){
        loadPopularMovies()
        loadNowPlayingMovies()
        loadTopRatedMovies()
    }

    private fun loadPopularMovies() {
        viewModelScope.launch {
            getPopularMoviesUseCase().collect { result ->
                when(result){
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(
                            popularLoading = true,
                        )
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(
                            popularLoading = false
                        )
                        result.message?.let { _event.emit(UiEvent.ShowErrorSnackBar(it)) }
                    }
                    is Resource.Success ->{
                        _state.value = _state.value.copy(
                            popularLoading = false,
                            popularMovies = result.data.orEmpty()
                        )
                    }
                }
            }
        }
    }

    private fun loadNowPlayingMovies() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collect { result ->
                when(result){
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(
                            nowPlayingLoading = true,
                        )
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(
                            nowPlayingLoading = false
                        )
                        result.message?.let { _event.emit(UiEvent.ShowErrorSnackBar(it)) }
                    }
                    is Resource.Success ->{
                        _state.value = _state.value.copy(
                            nowPlayingLoading = false,
                            nowPlayingMovies = result.data.orEmpty()
                        )
                    }
                }
            }
        }
    }

    private fun loadTopRatedMovies() {
        viewModelScope.launch {
            getTopRatedMoviesUseCase().collect { result ->
                when(result){
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(
                            topRatedLoading = true,
                        )
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(
                            topRatedLoading = false
                        )
                        result.message?.let { _event.emit(UiEvent.ShowErrorSnackBar(it)) }
                    }
                    is Resource.Success ->{
                        _state.value = _state.value.copy(
                            topRatedLoading = false,
                            topRatedMovies = result.data.orEmpty()
                        )
                    }
                }
            }
        }
    }

}