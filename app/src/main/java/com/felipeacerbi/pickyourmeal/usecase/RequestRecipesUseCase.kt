package com.felipeacerbi.pickyourmeal.usecase

import com.felipeacerbi.pickyourmeal.repository.RecipesRepository
import com.felipeacerbi.pickyourmeal.repository.model.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RequestRecipesUseCase @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val repository: RecipesRepository
) : UseCase<Flow<Result>> {

    override suspend fun run() =
        repository.getRecipes()
            .flowOn(dispatcher)
}