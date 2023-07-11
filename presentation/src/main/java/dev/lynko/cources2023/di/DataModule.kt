package dev.lynko.cources2023.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dev.lynko.cources2023.MyAnimalsApp
import dev.lynko.data.local.repository.AnimalsRepositoryImpl
import dev.lynko.data.network.repository.AuthorizationRepositoryImpl
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.repository.AuthorizationRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    single<AnimalsRepository> {
        AnimalsRepositoryImpl(
            animalsDao = MyAnimalsApp.INSTANCE.database.animalsDao(),
            context = androidContext()
        )
    }

    single<AuthorizationRepository> {
        AuthorizationRepositoryImpl(
            firebaseAuth = Firebase.auth
        )
    }

}