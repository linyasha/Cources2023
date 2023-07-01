package dev.lynko.data.repository


import dev.lynko.data.dao.AnimalsDao
import dev.lynko.data.model.AnimalModel
import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class AnimalsRepositoryImpl(private val animalsDao: AnimalsDao): AnimalsRepository {

    private val job = SupervisorJob()
    private val animalsScope = CoroutineScope(job + Dispatchers.IO)

    override suspend fun insertAnimal(animal: Animal) {
        animalsScope.launch {
            animalsDao.insert(AnimalModel.map(animal))
        }
    }

    override suspend fun getAllAnimas(): List<Animal> {
        return animalsScope.async {
            animalsDao.getAll().map { it.toAnimal() }
        }.await()
    }

    override fun getAllAnimasFlow(): Flow<List<Animal>> {
        return animalsDao.getAllFlow().flatMapConcat { list ->
            flowOf(list.map { it.toAnimal() })
        }
    }
}