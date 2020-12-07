package com.felipeacerbi.pickyourmeal.data.di

import android.content.Context
import androidx.room.Room
import com.felipeacerbi.pickyourmeal.BuildConfig
import com.felipeacerbi.pickyourmeal.data.local.RecipesDao
import com.felipeacerbi.pickyourmeal.data.local.RecipesDatabase
import com.felipeacerbi.pickyourmeal.data.local.RecipesDatabaseDataSource
import com.felipeacerbi.pickyourmeal.data.local.RecipesLocalDataSource
import com.felipeacerbi.pickyourmeal.data.remote.RecipesNetworkDataSource
import com.felipeacerbi.pickyourmeal.data.remote.RecipesRemoteDataSource
import com.felipeacerbi.pickyourmeal.data.remote.RecipesService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocalDataSource(
        databaseDataSource: RecipesDatabaseDataSource
    ): RecipesLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(
        networkDataSource: RecipesNetworkDataSource
    ): RecipesRemoteDataSource

    companion object {
        @Singleton
        @Provides
        fun provideRetrofit(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Singleton
        @Provides
        fun provideRecipesService(
            retrofit: Retrofit
        ): RecipesService =
            retrofit.create(RecipesService::class.java)

        @Singleton
        @Provides
        fun provideRecipesDatabase(
            @ApplicationContext context: Context
        ): RecipesDatabase =
            Room.databaseBuilder(context, RecipesDatabase::class.java,
                RecipesDatabase.RECIPES_DATABASE_NAME
            ).build()

        @Singleton
        @Provides
        fun provideRecipesDao(
            recipesDatabase: RecipesDatabase
        ): RecipesDao =
            recipesDatabase.recipesDao()
    }
}