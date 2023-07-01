package dev.lynko.domain.repository

import dev.lynko.domain.models.Animal
import kotlinx.coroutines.flow.Flow

interface AnimalsRepository {

    suspend fun insertAnimal(animal: Animal)
    suspend fun getAllAnimas(): List<Animal>
    fun getAllAnimasFlow(): Flow<List<Animal>>
}
