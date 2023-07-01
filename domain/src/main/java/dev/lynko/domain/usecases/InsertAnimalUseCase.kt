package dev.lynko.domain.usecases

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository

class InsertAnimalUseCase(private val animalsRepository: AnimalsRepository) {

    suspend fun execute(animal: Animal) {
        return animalsRepository.insertAnimal(animal)
    }

}