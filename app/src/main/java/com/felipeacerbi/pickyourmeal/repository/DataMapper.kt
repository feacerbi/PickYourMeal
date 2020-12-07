package com.felipeacerbi.pickyourmeal.repository

import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity
import com.felipeacerbi.pickyourmeal.data.local.model.RecipeEntity.Companion.DIVIDER
import com.felipeacerbi.pickyourmeal.data.remote.model.RecipeDescription
import com.felipeacerbi.pickyourmeal.repository.model.Recipe

fun List<RecipeEntity>.toRecipes() = map {
    Recipe(it.id, it.title, it.headline, it.picture, it.tags.split(DIVIDER))
}

fun List<RecipeDescription>.toEntities() = map {
    RecipeEntity(it.id, it.name, it.headline, it.image, "")
}