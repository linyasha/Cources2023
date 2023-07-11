package dev.lynko.cources2023.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lynko.cources2023.ui.viewModel.AnimalsViewModelFactory
import dev.lynko.domain.usecases.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.InsertAnimalUseCase

@Module
class PresentationModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

//    @Provides
//    fun provideAnimalsViewModelFactory(
//        getAnimalUseCase: GetAllAnimalsUseCase,
//        getFlowAnimalUseCase: GetFlowAnimalsUseCase,
//        insertAnimalUseCase: InsertAnimalUseCase
//    ): AnimalsViewModelFactory {
//        return AnimalsViewModelFactory(getAnimalUseCase, getFlowAnimalUseCase, insertAnimalUseCase)
//    }

}