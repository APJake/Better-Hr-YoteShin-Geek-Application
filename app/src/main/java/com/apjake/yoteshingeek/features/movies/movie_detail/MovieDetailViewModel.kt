package com.apjake.yoteshingeek.features.movies.movie_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apjake.yoteshingeek.common.util.Resource
import com.apjake.yoteshingeek.domain.usecase.GetMovieDetailUseCase
import com.apjake.yoteshingeek.features.movies.home.HomeUiState
import com.apjake.yoteshingeek.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase
): ViewModel() {

    private val _state = MutableStateFlow(MovieDetailUiState())
    val state = _state.asStateFlow()

    private var _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()

    fun loadMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetailUseCase(movieId).collect { result ->
                when(result){
                    is Resource.Loading ->{
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }
                    is Resource.Error ->{
                        _state.value = _state.value.copy(
                            isLoading = false
                        )
                        result.message?.let { _event.send(UiEvent.ShowErrorSnackBar(it)) }
                    }
                    is Resource.Success ->{
                        _state.value = _state.value.copy(
                            isLoading = false,
                            movieDetail = result.data
                        )
                    }
                }
            }
        }
    }

}