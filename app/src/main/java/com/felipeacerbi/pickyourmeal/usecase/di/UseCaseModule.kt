package com.felipeacerbi.pickyourmeal.usecase.di

import com.felipeacerbi.pickyourmeal.repository.RecipesRepository
import com.felipeacerbi.pickyourmeal.repository.model.Result
import com.felipeacerbi.pickyourmeal.usecase.ForceRefreshRecipesUseCase
import com.felipeacerbi.pickyourmeal.usecase.RequestRecipesUseCase
import com.felipeacerbi.pickyourmeal.usecase.UseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
class UseCaseModule {

    @RequestRecipes
    @Provides
    fun provideRequestRecipesUseCase(
        recipesRepository: RecipesRepository
    ): UseCase<Flow<Result>> = RequestRecipesUseCase(Dispatchers.IO, recipesRepository)

    @ForceRefreshRecipes
    @Provides
    fun provideForceRefreshRecipesUseCase(
        recipesRepository: RecipesRepository
    ): UseCase<Result> = ForceRefreshRecipesUseCase(Dispatchers.IO, recipesRepository)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RequestRecipes

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class ForceRefreshRecipes
}