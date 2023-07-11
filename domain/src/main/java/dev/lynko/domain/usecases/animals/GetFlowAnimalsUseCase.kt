package dev.lynko.domain.usecases.animals

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import kotlinx.coroutines.flow.Flow

class GetFlowAnimalsUseCase(private val repository: AnimalsRepository) {

    fun execute(): Flow<List<Animal>> {
        return repository.getAllAnimasFlow()
    }

}