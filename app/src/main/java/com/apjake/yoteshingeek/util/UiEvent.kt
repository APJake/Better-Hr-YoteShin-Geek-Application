package com.apjake.yoteshingeek.util

sealed class UiEvent {
    data class ShowErrorSnackBar(val message: String): UiEvent()
}