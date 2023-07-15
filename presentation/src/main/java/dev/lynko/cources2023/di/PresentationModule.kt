package dev.lynko.cources2023.di

import dev.lynko.cources2023.service.AnimalsNotificationManager
import dev.lynko.cources2023.service.MyFirebaseMessagingService
import dev.lynko.cources2023.ui.viewModel.AnimalsViewModel
import dev.lynko.cources2023.ui.viewModel.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AnimalsViewModel> {
        AnimalsViewModel(
            getAnimalUseCase = get(),
            getFlowAnimalUseCase = get(),
            insertAnimalUseCase = get()
        )
    }

    viewModel<AuthViewModel> {
        AuthViewModel(
            loginUserUseCase = get(),
            registrationUserUseCase = get()
        )
    }

    single<AnimalsNotificationManager> {
        AnimalsNotificationManager(
            context = androidContext()
        )
    }
}