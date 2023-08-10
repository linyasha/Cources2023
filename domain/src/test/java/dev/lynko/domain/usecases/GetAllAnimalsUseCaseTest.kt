package dev.lynko.domain.usecases

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetAllAnimalsUseCaseTest {

    //`

//    class TestRepository: AnimalsRepository {
//        override suspend fun insertAnimal(animal: Animal) {
//            TODO("Not yet implemented")
//        }
//
//        override suspend fun getAllAnimas(): List<Animal> {
//            return listOf(
//                Animal(
//                type = 1,
//                "test animal",
//                age = 15,
//                weight = 15.1
//            )
//            )
//        }
//
//        override fun getAllAnimasFlow(): Flow<List<Animal>> {
//            TODO("Not yet implemented")
//        }
//
//    }

    @Test
    fun `should return all animals from repository`() = runTest {
        val repository = mock<AnimalsRepository>()

        val testUseCase = GetAllAnimalsUseCase(
            repository = repository
        )

        val expected = listOf(Animal(
            type = 1,
            "test animal",
            age = 15,
            weight = 15.1
            )
        )
        Mockito.`when`(repository.getAllAnimas()).thenReturn(expected)

        val actual = testUseCase.execute()
        Assertions.assertEquals(expected, actual)

    }
}