package dev.lynko.domain.usecases.animals

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository

class GetAllAnimalsUseCase(private val repository: AnimalsRepository) {

    suspend fun execute(): List<Animal> {
        return repository.getAllAnimas()
    }

}