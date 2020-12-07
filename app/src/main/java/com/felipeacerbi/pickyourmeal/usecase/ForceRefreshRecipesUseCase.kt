package com.felipeacerbi.pickyourmeal.usecase

import com.felipeacerbi.pickyourmeal.repository.RecipesRepository
import com.felipeacerbi.pickyourmeal.repository.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ForceRefreshRecipesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RecipesRepository
) : UseCase<Result> {

    override suspend fun run() = withContext(dispatcher) {
        repository.forceRefresh()
    }
}