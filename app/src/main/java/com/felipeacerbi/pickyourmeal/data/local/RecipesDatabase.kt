package com.felipeacerbi.pickyourmeal.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class RecipesDatabase : RoomDatabase() {
    abstract fun recipesDao(): RecipesDao

    companion object {
        const val RECIPES_DATABASE_NAME = "RecipesDatabase"
    }
}