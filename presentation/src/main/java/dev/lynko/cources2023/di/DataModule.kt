package dev.lynko.cources2023.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.data.repository.AnimalsRepositoryImpl
import dev.lynko.domain.repository.AnimalsRepository


@Module
class DataModule {

    @Provides
    fun provideAnimalsRepository(context: Context): AnimalsRepository {
        return AnimalsRepositoryImpl(MyAnimalsApp.INSTANCE.database.animalsDao(), context)
    }

}