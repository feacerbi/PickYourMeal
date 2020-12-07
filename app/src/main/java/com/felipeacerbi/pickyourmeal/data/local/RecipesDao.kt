package com.felipeacerbi.pickyourmeal.data.local

import androidx.room.*
import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity
import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity.Companion.RECIPES_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Query("SELECT * FROM $RECIPES_TABLE_NAME")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipes(recipe: List<RecipeEntity>)

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)
}