package com.felipeacerbi.pickyourmeal.data.remote

import com.felipeacerbi.pickyourmeal.data.remote.model.RecipeDescription
import javax.inject.Inject

class RecipesNetworkDataSource @Inject constructor(
    private val recipesService: RecipesService
) : RecipesRemoteDataSource {

    override suspend fun getAllRecipes(): List<RecipeDescription> =
        recipesService.getRecipes()
}