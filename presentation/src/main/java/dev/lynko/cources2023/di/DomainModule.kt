package dev.lynko.cources2023.di

import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.data.local.repository.AnimalsRepositoryImpl
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.usecases.animals.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.animals.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.animals.InsertAnimalUseCase
import dev.lynko.domain.usecases.user.LoginUserUseCase
import dev.lynko.domain.usecases.user.RegistrationUserUseCase
import org.koin.android.ext.koin.androidContext
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

    factory<LoginUserUseCase> {
        LoginUserUseCase(
            authRepository = get()
        )
    }

    factory<RegistrationUserUseCase> {
        RegistrationUserUseCase(
            authRepository = get()
        )
    }

}