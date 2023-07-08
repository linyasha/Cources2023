package dev.lynko.cources2023.ui.viewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow

fun main() {

    GlobalScope.launch(Dispatchers.IO) {

        withContext(Dispatchers.Main) {
            getData().collect {

            }
        }
        val listOfNumbers = listOf<Int>(1, 2, 3, 4, 5)
        listOfNumbers.asFlow().collect { listNumber ->
            println("$listNumber")
        }
    }



//    myFlow.collect { number ->
//        println("$number")
//    }

}

suspend fun getData(): Flow<Int> = flow<Int> {
    withContext(Dispatchers.IO) {
        for (i in 0..20) {
            emit(i)
            delay(1000L)
        }
    }
}

//flowOf
//count, take, first, last, drop, single
//map, transform(emit)
//filter, takeWhile, dropWhile
//reduce{a, b} fold{init, {a, b}}
//zip
//StateFlow, SharedFlow
//public fun  MutableSharedFlow(
//    replay: Int = 0, // 1
//    extraBufferCapacity: Int = 0, // 2
//    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND // 3
//): MutableSharedFlow
