package dev.lynko.domain.usecases

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import javax.inject.Inject

class InsertAnimalUseCase(private val animalsRepository: AnimalsRepository) {

    suspend fun execute(animal: Animal) {
        return animalsRepository.insertAnimal(animal)
    }

}