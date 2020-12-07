package com.felipeacerbi.pickyourmeal.data.local

import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSource {
    fun getAllRecipes(): Flow<List<RecipeEntity>>
    suspend fun saveRecipes(recipes: List<RecipeEntity>)
    suspend fun removeRecipe(recipe: RecipeEntity)
}