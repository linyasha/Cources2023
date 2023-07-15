package dev.lynko.domain.usecases.user

import dev.lynko.domain.models.Animal
import dev.lynko.domain.models.Status
import dev.lynko.domain.models.UserCreds
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.repository.AuthorizationRepository

class LoginUserUseCase(private val authRepository: AuthorizationRepository) {

    suspend fun execute(userCreds: UserCreds): Status {
        return authRepository.login(userCreds)
    }

}