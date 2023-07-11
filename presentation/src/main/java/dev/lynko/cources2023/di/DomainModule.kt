package dev.lynko.cources2023.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.data.repository.AnimalsRepositoryImpl
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.usecases.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.InsertAnimalUseCase
import javax.inject.Singleton

@Module
class DomainModule {

    @Provides
    fun provideGetAllAnimalsUseCase(repository: AnimalsRepository): GetAllAnimalsUseCase {
        return GetAllAnimalsUseCase(repository)
    }

    @Provides
    fun provideGetFlowAnimalsUseCase(repository: AnimalsRepository): GetFlowAnimalsUseCase {
        return GetFlowAnimalsUseCase(repository)
    }

    @Provides
    fun provideInsertAnimalUseCase(repository: AnimalsRepository): InsertAnimalUseCase {
        return InsertAnimalUseCase(repository)
    }

}