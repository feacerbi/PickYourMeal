package com.felipeacerbi.pickyourmeal.data.remote

import com.felipeacerbi.pickyourmeal.data.remote.model.RecipeDescription
import retrofit2.http.GET

interface RecipesService {

    @GET("recipes.json")
    suspend fun getRecipes(): List<RecipeDescription>
}