package dev.lynko.cources2023.di

import dev.lynko.cources2023.ui.viewModel.AnimalsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel<AnimalsViewModel> { (accountId: Int) ->
        AnimalsViewModel(
            getAnimalUseCase = get(),
            getFlowAnimalUseCase = get(),
            insertAnimalUseCase = get(),
            accountId = accountId
        )
    }

}