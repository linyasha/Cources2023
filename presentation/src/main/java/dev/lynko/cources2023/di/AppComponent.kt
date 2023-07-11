package dev.lynko.cources2023.di

import dagger.Component
import dev.lynko.cources2023.ui.activity.AnimalsActivity

@Component(modules = [PresentationModule::class, DomainModule::class, DataModule::class])
interface AppComponent {
    fun inject(animalsActivity: AnimalsActivity)
}