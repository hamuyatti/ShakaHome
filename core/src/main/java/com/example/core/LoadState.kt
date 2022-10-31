package com.example.core

sealed interface LoadState<T> {
    object Empty : LoadState<Nothing>
    object Loading : LoadState<Nothing>
    data class Loaded<T>(val data: T) : LoadState<T>
    data class Error(val error: Throwable) : LoadState<Nothing>
}