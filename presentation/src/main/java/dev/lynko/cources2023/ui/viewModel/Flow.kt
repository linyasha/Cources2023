package dev.lynko.cources2023.ui.viewModel

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

data class Person(val id: Int, val name: String, val age: Int)

data class Car(val id: Int, val ownerId: Int, val model: String)

suspend fun main() {



    //count, take, first, last, drop, single
    GlobalScope.async(Dispatchers.IO) {

//        val count = getData().count { element -> element > 15 }
//        val count1 = getData().count()

//        val take = getData().take(5).collect { value ->
//            //sdasdasdas
//        }
//        0 1 2 3


//        val a = getData().firstOrNull()
//        val b = getData().lastOrNull()
//
//
//        val list = mutableListOf<Int>(1, 2, 3)
//        list.takeIf { it.isNotEmpty() }?.forEach {
//
//        }

//        val flow = getData()
//        val count = flow.count()
//        flow.drop(count - 5).collect {
//            println(it)
//        }

//        val a = getData()


//        val persons = listOf(
//            Person(1, "Name", 1),
//            Person(2, "Name1", 2),
//            Person(3, "Name2", 3),
//            Person(4, "Name3", 4),
//            Person(5, "Name4", 5)
//        ).asFlow().map { it.name }
//
//        val cars = listOf(
//            Car(1, 1, "Tesla"),
//            Car(2, 1, "Audi"),
//            Car(3, 2, "BMW"),
//            Car(4, 3, "Opel"),
//            Car(5, 4, "Merc")
//        ).asFlow().map { it.model }
//
//        persons.zip(cars) { p: String, c: String ->
//            "$p | $c"
//        }.collect {
//            println(it)
//        }




//        persons.takeWhile { it.age < 4 }.collect {
//            println(it)
//        }
//
//        persons.dropWhile { it.age < 3 }.collect {
//            println(it)
//        }
//
//        persons.filter { it.age >= 3 }.collect {
//            println(it)
//        }

//        persons.transform { element ->
//            if (element.age < 4) {
//                emit(element.name)
//            }
//        }.collect { person ->
//            println(person)
//        }
//
//        persons.filter { it.age < 4 }.map { it.name }.collect {
//            println(it)
//        }


//        println(a)
//        println(b)

//        print(count)
//        print(count1)
//        val listOfNumbers = listOf<Int>(1, 2, 3, 4, 5)
//        listOfNumbers.asFlow().collect { listNumber ->
//            println("$listNumber")
//        }
        /*
        flowOf(1, 2, 3, 4, 5, 6, "dff")
        flow<Int> {
            withContext(Dispatchers.IO) {
                for (i in 0..20) {
                    emit(i)
                    delay(1000L)
                }
            }
        }
        listOf<Int>(1, 2, 3, 4, 5).asFlow()

         */

//        getData().collect {
//            delay(500L)
//            println(it)
//        }

        getData().collectLatest {
            //1
            delay(500L) // -> writeToDB()  <-
            println(it)
        }


    }.await()



//    myFlow.collect { number ->
//        println("$number")
//    }

}

suspend fun getData(): Flow<Int> = flow<Int> {
    //sadasd
    for (i in 0..20) {
        if (i < 10) {
            emit(i)
        } else {
            delay(1000L)
            emit(i)
        }
//        delay(1000L)
    }
}

//SharedFlow  <- горячий


//StateFlow <- горячий

//Flow   <- холодный



//map, transform(emit)
//filter, takeWhile, dropWhile
//zip
//collect and collectLatest
//StateFlow, SharedFlow
//public fun  MutableSharedFlow(
//    replay: Int = 0, // 1
//    extraBufferCapacity: Int = 0, // 2
//    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND // 3
//): MutableSharedFlow
