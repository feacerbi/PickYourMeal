package com.felipeacerbi.pickyourmeal.repository

import com.felipeacerbi.pickyourmeal.repository.model.Result
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getRecipes(): Flow<Result>
    suspend fun forceRefresh(): Result
}