package dev.lynko.data.network.repository

import com.google.firebase.auth.FirebaseAuth
import dev.lynko.domain.models.UserCreds
import dev.lynko.domain.repository.AuthorizationRepository

class AuthorizationRepositoryImpl(private val firebaseAuth: FirebaseAuth): AuthorizationRepository {

    override suspend fun login(userCreds: UserCreds) {

    }

    override suspend fun registration(userCreds: UserCreds) {

    }
}