package dev.lynko.domain.repository

import dev.lynko.domain.models.UserCreds

interface AuthorizationRepository {

    suspend fun login(userCreds: UserCreds)
    suspend fun registration(userCreds: UserCreds)

}
