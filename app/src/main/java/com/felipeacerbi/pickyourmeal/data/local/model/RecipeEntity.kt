package com.felipeacerbi.pickyourmeal.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity.Companion.RECIPES_TABLE_NAME

@Entity(tableName = RECIPES_TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey @ColumnInfo(name = RECIPE_ID_COLUMN_NAME) val id: String,
    @ColumnInfo(name = RECIPE_TITLE_COLUMN_NAME) val title: String,
    @ColumnInfo(name = RECIPE_HEADLINE_COLUMN_NAME) val headline: String,
    @ColumnInfo(name = RECIPE_PICTURE_COLUMN_NAME) val picture: String,
    @ColumnInfo(name = RECIPE_TAGS_COLUMN_NAME) val tags: String
) {
    companion object {
        const val RECIPES_TABLE_NAME = "recipes"
        const val RECIPE_ID_COLUMN_NAME = "recipe_id"
        const val RECIPE_TITLE_COLUMN_NAME = "recipe_title"
        const val RECIPE_HEADLINE_COLUMN_NAME = "recipe_headline"
        const val RECIPE_PICTURE_COLUMN_NAME = "recipe_picture"
        const val RECIPE_TAGS_COLUMN_NAME = "recipe_tags"

        const val DIVIDER = ";"
    }
}