package dev.lynko.domain.usecases.user

import dev.lynko.domain.models.Animal
import dev.lynko.domain.models.UserCreds
import dev.lynko.domain.repository.AnimalsRepository
import dev.lynko.domain.repository.AuthorizationRepository

class LoginUserUseCase(private val authRepository: AuthorizationRepository) {

    suspend fun execute(userCreds: UserCreds) {
        return authRepository.login(userCreds)
    }

}