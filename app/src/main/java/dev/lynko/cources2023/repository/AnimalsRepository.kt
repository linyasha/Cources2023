package dev.lynko.cources2023.repository

import dev.lynko.cources2023.database.dao.AnimalsDao
import dev.lynko.cources2023.model.Animal
import kotlinx.coroutines.*

class AnimalsRepository(val animalsDao: AnimalsDao) {

    private val job = SupervisorJob()
    private val animalsScope = CoroutineScope(job + Dispatchers.IO)


    suspend fun insertAnimal(animal: Animal) {
        animalsScope.launch {
            animalsDao.insert(animal)
        }
    }

    suspend fun getAllAnimas(): List<Animal> {
        return animalsScope.async {
            animalsDao.getAll()
        }.await()
    }
}