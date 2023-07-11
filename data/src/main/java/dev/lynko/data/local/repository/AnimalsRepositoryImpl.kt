package dev.lynko.data.local.repository


import android.content.Context
import android.util.Log
import dev.lynko.data.local.dao.AnimalsDao
import dev.lynko.data.local.model.AnimalModel
import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.math.log

class AnimalsRepositoryImpl(
    private val animalsDao: AnimalsDao,
    private val context: Context
    ): AnimalsRepository {

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
        Log.d("ABC", "getAllAnimasFlow: $context")
        return animalsDao.getAllFlow().flatMapConcat { list ->
            flowOf(list.map { it.toAnimal() })
        }
    }
}