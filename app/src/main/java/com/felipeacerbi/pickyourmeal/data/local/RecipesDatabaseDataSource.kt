package com.felipeacerbi.pickyourmeal.data.local

import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesDatabaseDataSource @Inject constructor(
    private val recipesDao: RecipesDao
) : RecipesLocalDataSource {

    override fun getAllRecipes(): Flow<List<RecipeEntity>> = recipesDao.getRecipes()

    override suspend fun saveRecipes(recipes: List<RecipeEntity>) {
        recipesDao.insertRecipes(recipes)
    }

    override suspend fun removeRecipe(recipe: RecipeEntity) {
        recipesDao.deleteRecipe(recipe)
    }
}