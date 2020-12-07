package com.felipeacerbi.pickyourmeal.viewstate

import com.felipeacerbi.pickyourmeal.repository.model.Recipe
import java.util.*

sealed class RecipesViewStateReducer : ViewStateReducer<RecipesViewState> {

    data class Recipes(
        val list: List<Recipe>?
    ) : RecipesViewStateReducer() {
        override val reduce: (RecipesViewState) -> RecipesViewState = {
            it.copy(
                recipes = list ?: listOf(),
                loading = false,
                currentDate = Calendar.getInstance(),
                showEmpty = list?.isEmpty() ?: true)
        }
    }

    object Loading : RecipesViewStateReducer() {
        override val reduce: (RecipesViewState) -> RecipesViewState = {
            it.copy(
                loading = true)
        }
    }

    object FinishedLoading : RecipesViewStateReducer() {
        override val reduce: (RecipesViewState) -> RecipesViewState = {
            it.copy(
                loading = false)
        }
    }

    object Error : RecipesViewStateReducer() {
        override val reduce: (RecipesViewState) -> RecipesViewState = {
            it.copy(
                loading = false,
                showEmpty = it.recipes.isEmpty())
        }
    }
}