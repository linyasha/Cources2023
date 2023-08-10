package dev.lynko.domain.usecases

import dev.lynko.domain.models.Animal
import dev.lynko.domain.repository.AnimalsRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class InsertAnimalUseCaseTest {

    private val repository = mock<AnimalsRepository>()

    @AfterEach
    fun afterEach() {
        Mockito.reset(repository)
    }

    @BeforeEach
    fun beforeAll() {

    }

    @Test
    fun `should return false when save animal which contains in database`() = runTest {
            val testListAnimals = listOf<Animal>(
                getRandomAnimal1()
            )
            val testAnimal = getRandomAnimal1()
            Mockito.`when`(repository.getAllAnimas()).thenReturn(testListAnimals)
            Mockito.`when`(repository.insertAnimal(testAnimal)).thenReturn(true)

            val useCase = InsertAnimalUseCase(animalsRepository = repository)

            val actual = useCase.execute(testAnimal)
            val expected = false
            Assertions.assertEquals(expected, actual)
            Mockito.verify(repository, Mockito.never()).insertAnimal(testAnimal)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun `should return true and save animal in database`(argument: Int) = runTest {
        val testListAnimals = listOf<Animal>(
            getRandomAnimal1()
        )
        val expected = argument == 1
        val testAnimal = getRandomAnimal2()
        Mockito.`when`(repository.getAllAnimas()).thenReturn(testListAnimals)
        Mockito.`when`(repository.insertAnimal(testAnimal)).thenReturn(expected)

        val useCase = InsertAnimalUseCase(animalsRepository = repository)

        val actual = useCase.execute(testAnimal)
        Assertions.assertEquals(expected, actual)
        Mockito.verify(repository, Mockito.times(1)).insertAnimal(testAnimal)
    }

    fun getRandomAnimal1(): Animal = Animal(
        type = 1,
        "test animal 1",
        age = 1,
        weight = 1.1,
        id = 1
    )

    fun getRandomAnimal2(): Animal = Animal(
        type = 2,
        "test animal 2",
        age = 2,
        weight = 2.2,
        id = 2
    )

}