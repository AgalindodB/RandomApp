package com.example.randomapp.di

// Importamos las correspondientes clases o librerias para el correcto funcionamiento de nuestra app
import com.example.randomapp.netwok.PostApiService
import com.example.randomapp.repository.PostRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApiService
    ): PostRepository = PostRepository(api)

}