package dev.lynko.domain.usecases

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import javax.inject.Inject

class GetAllAnimalsUseCase(private val repository: AnimalsRepository) {

    suspend fun execute(): List<Animal> {
        return repository.getAllAnimas()
    }

}