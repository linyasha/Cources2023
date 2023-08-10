package dev.lynko.cources2023.ui.viewModel

//import androidx.arch.core.executor.ArchTaskExecutor
//import androidx.arch.core.executor.TaskExecutor
//import dev.lynko.domain.models.Animal
import MainDispatcherRule
import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.lynko.cources2023.ui.model.ValidateState
import dev.lynko.domain.models.Animal
import dev.lynko.domain.usecases.GetAllAnimalsUseCase
import dev.lynko.domain.usecases.GetFlowAnimalsUseCase
import dev.lynko.domain.usecases.InsertAnimalUseCase
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
//import org.junit.jupiter.api.AfterEach
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.Assertions.assertEquals
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.mockito.Mockito
import org.mockito.kotlin.mock
//import java.lang.Byte

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mockito
import org.mockito.kotlin.any

class AnimalsViewModelTest {

    val getAllAnimalsUseCase: GetAllAnimalsUseCase = mock<GetAllAnimalsUseCase>()
    val insertAllAnimalsUseCase: InsertAnimalUseCase = mock<InsertAnimalUseCase>()
    val getFlowAnimalsUseCase: GetFlowAnimalsUseCase = mock<GetFlowAnimalsUseCase>()

    @Before
    fun afterEach() {
        Mockito.reset(getAllAnimalsUseCase)
        Mockito.reset(insertAllAnimalsUseCase)
        Mockito.reset(getFlowAnimalsUseCase)
    }

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should dont call insert animal use case when data is not valid and state fail`() = runTest {
        val viewModel = getAnimalViewModel()
        Mockito.`when`(insertAllAnimalsUseCase.execute(any())).thenReturn(true)
        Mockito.`when`(getFlowAnimalsUseCase.execute()).thenReturn(emptyFlow())
        val testName = "test name"
        val testAge = "wrong age"
        val testWeight = "15.5"
        val testType = (1).toByte()
        val expectedState = ValidateState.FAIL
        viewModel.insertAnimal(testName, testAge, testWeight, testType)
        val actualState = viewModel.state.value
        assertEquals(expectedState, actualState)
        Mockito.verify(insertAllAnimalsUseCase, Mockito.never()).execute(any())
    }

    @Test
    fun `should call insert animal use case once when data is valid and state success`() = runTest {
        val viewModel = getAnimalViewModel()
        Mockito.`when`(insertAllAnimalsUseCase.execute(any())).thenReturn(true)
        Mockito.`when`(getFlowAnimalsUseCase.execute()).thenReturn(emptyFlow())
        val testName = "test name"
        val testAge = "15"
        val testWeight = "15.5"
        val testType = (1).toByte()
        val expectedState = ValidateState.SUCCESS
        val expected = Animal(
            name = "test name",
            age = 15,
            weight = 15.5,
            type = 1
        )
        viewModel.insertAnimal(testName, testAge, testWeight, testType)
        val actualState = viewModel.state.value
        assertEquals(expectedState, actualState)
        Mockito.verify(insertAllAnimalsUseCase, Mockito.times(1)).execute(expected)
    }

    @Test
    fun `should call get all animal use case when call getAllAnimal method`() = runTest {
        val viewModel = getAnimalViewModel()
        Mockito.`when`(getAllAnimalsUseCase.execute()).thenReturn(listOf())
        Mockito.`when`(getFlowAnimalsUseCase.execute()).thenReturn(emptyFlow())
        viewModel.getAllAnimals()
        Mockito.verify(getAllAnimalsUseCase, Mockito.times(1)).execute()
    }

    fun getAnimalViewModel(): AnimalsViewModel = AnimalsViewModel(
        getAllAnimalsUseCase,
        getFlowAnimalsUseCase,
        insertAllAnimalsUseCase,
        TEST_ACCOUNT_ID
    )

    companion object {

        const val TEST_ACCOUNT_ID = 12
    }
}