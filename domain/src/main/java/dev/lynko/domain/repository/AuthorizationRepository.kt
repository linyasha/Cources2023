package dev.lynko.domain.repository

import dev.lynko.domain.models.Status
import dev.lynko.domain.models.UserCreds

interface AuthorizationRepository {

    suspend fun login(userCreds: UserCreds): Status
    suspend fun registration(userCreds: UserCreds): Status

}
