package com.felipeacerbi.pickyourmeal.repository.di

import com.felipeacerbi.pickyourmeal.repository.RecipesRepository
import com.felipeacerbi.pickyourmeal.repository.RecipesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository
}