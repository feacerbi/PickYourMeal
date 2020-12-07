package com.felipeacerbi.pickyourmeal.viewstate

import androidx.annotation.StringRes

sealed class RecipesViewEffect : ViewEffect {
    data class ErrorMessage(@StringRes val error: Int) : RecipesViewEffect()
}