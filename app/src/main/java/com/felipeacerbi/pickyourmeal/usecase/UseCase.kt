package com.felipeacerbi.pickyourmeal.usecase

interface UseCase<T> {
    suspend fun run(): T
}