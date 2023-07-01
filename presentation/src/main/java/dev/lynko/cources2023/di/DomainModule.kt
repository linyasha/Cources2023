package dev.lynko.cources2023.di

import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.data.repository.AnimalsRepositoryImpl
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.usecases.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.InsertAnimalUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<InsertAnimalUseCase> {
        InsertAnimalUseCase(
            animalsRepository = get()
        )
    }

    factory<GetAllAnimalsUseCase> {
        GetAllAnimalsUseCase(
            repository = get()
        )
    }

    factory<GetFlowAnimalsUseCase> {
        GetFlowAnimalsUseCase(
            repository = get()
        )
    }

    single<AnimalsRepository> {
        AnimalsRepositoryImpl(
            animalsDao = MyAnimalsApp.INSTANCE.database.animalsDao()
        )
    }

}