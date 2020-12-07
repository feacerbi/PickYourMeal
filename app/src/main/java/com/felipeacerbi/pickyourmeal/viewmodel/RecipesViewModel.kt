package com.felipeacerbi.pickyourmeal.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.felipeacerbi.pickyourmeal.repository.model.Recipe
import com.felipeacerbi.pickyourmeal.repository.model.Result
import com.felipeacerbi.pickyourmeal.usecase.UseCase
import com.felipeacerbi.pickyourmeal.usecase.di.UseCaseModule.ForceRefreshRecipes
import com.felipeacerbi.pickyourmeal.usecase.di.UseCaseModule.RequestRecipes
import com.felipeacerbi.pickyourmeal.util.collectLatestSuccess
import com.felipeacerbi.pickyourmeal.util.handleError
import com.felipeacerbi.pickyourmeal.util.onFailResult
import com.felipeacerbi.pickyourmeal.util.safeLaunch
import com.felipeacerbi.pickyourmeal.viewmodel.RecipesViewModel.Action.ForceRefresh
import com.felipeacerbi.pickyourmeal.viewstate.RecipesViewEffect
import com.felipeacerbi.pickyourmeal.viewstate.RecipesViewEffect.ErrorMessage
import com.felipeacerbi.pickyourmeal.viewstate.RecipesViewState
import com.felipeacerbi.pickyourmeal.viewstate.RecipesViewStateReducer.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

class RecipesViewModel @ViewModelInject constructor(
    @RequestRecipes private val requestRecipesUseCase: UseCase<Flow<Result>>,
    @ForceRefreshRecipes private val forceRefreshRecipesUseCase: UseCase<Result>
) : StateViewModel<RecipesViewState, RecipesViewEffect>(RecipesViewState()), CoroutineScope {

    fun getStateStream() = viewState
    fun getEffectStream() = viewEffect

    init {
        startRecipesFlow()
    }

    fun perform(action: Action) {
        when (action) {
            is ForceRefresh -> forceRefresh()
        }
    }

    private fun forceRefresh() = safeLaunch(::showError) {
        updateState(Loading)

        forceRefreshRecipesUseCase.run()

        updateState(FinishedLoading)
    }

    private fun startRecipesFlow() = safeLaunch(::showError) {
        updateState(Loading)

        requestRecipesUseCase.run()
            .onFailResult { showError(it.error) }
            .collectLatestSuccess<List<Recipe>> {
                updateState(Recipes(it))
            }
    }

    private fun showError(exception: Exception) {
        updateState(Error)
        updateEffect(ErrorMessage(exception.handleError()))
    }

    sealed class Action {
        object ForceRefresh : Action()
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}