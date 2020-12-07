package com.felipeacerbi.pickyourmeal.data.remote

import com.felipeacerbi.pickyourmeal.data.remote.model.RecipeDescription

interface RecipesRemoteDataSource {
    suspend fun getAllRecipes(): List<RecipeDescription>
}