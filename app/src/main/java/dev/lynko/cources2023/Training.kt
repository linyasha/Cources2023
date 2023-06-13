package dev.lynko.cources2023

import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

//Repeat
suspend fun main() {
    val coroutineScope = CoroutineScope(Job())
    val job = coroutineScope.launch {
        val firstChild = coroutineScope.launch(Job()) {
            println("The first child")
            launch {
                println("Child first child start")
                delay(6000L)
                println("Child first child end")
            }
            delay(1000L)
            throw NullPointerException()
        }
        val secondChild = coroutineScope.launch(Job()) {
            println("The second child")
            delay(10000L)
            println("The second end")
        }
        delay(15000L)
    }
    job.join()
}