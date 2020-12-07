package com.felipeacerbi.pickyourmeal.repository.model

sealed class Result {
    data class Success<T>(val data: T) : Result()
    data class Fail(val error: Exception) : Result()
}