package com.felipeacerbi.pickyourmeal.repository

import com.felipeacerbi.pickyourmeal.data.local.RecipesLocalDataSource
import com.felipeacerbi.pickyourmeal.data.remote.RecipesRemoteDataSource
import com.felipeacerbi.pickyourmeal.repository.model.Result
import com.felipeacerbi.pickyourmeal.util.emitAllResults
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val localDataSource: RecipesLocalDataSource,
    private val remoteDataSource: RecipesRemoteDataSource
) : RecipesRepository {

    override fun getRecipes() = flow {
        val result = forceRefresh()
        if (result is Result.Fail) {
            emit(result)
        }

        emitAllResults(
            localDataSource.getAllRecipes()
                .map { it.toRecipes() }
        )
    }

    override suspend fun forceRefresh() = runWithResult {
        val remoteRecipes = remoteDataSource.getAllRecipes()
        localDataSource.saveRecipes(remoteRecipes.toEntities())
    }

    private suspend fun <T> runWithResult(
        block: suspend () -> T
    ): Result =
        try {
            Result.Success(block.invoke())
        } catch (exception: Exception) {
            Result.Fail(exception)
        }
}