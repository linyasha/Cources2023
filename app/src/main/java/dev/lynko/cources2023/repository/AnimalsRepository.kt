package dev.lynko.cources2023.repository

import dev.lynko.cources2023.database.dao.AnimalsDao
import dev.lynko.cources2023.model.Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AnimalsRepository(private val animalsDao: AnimalsDao) {

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

    fun getAllAnimasLiveData() = animalsDao.getAllLiveData()
    suspend fun deleteAllAnimals() {
        animalsScope.launch {
            val animals = getAllAnimas()
            for (animal in animals) {
                animalsDao.deleteAnimal(animal.id)
            }
        }
        //TODO(Добавьте метод deleteAll, который будет полностью удалять всех питомцев из таблицы. Пока
        // его можно не вызывать, поработаем с ним позже)
    }}