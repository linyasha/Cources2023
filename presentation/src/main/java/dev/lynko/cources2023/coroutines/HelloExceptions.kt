package dev.lynko.cources2023.coroutines

import kotlinx.coroutines.*

fun main() {
//    try {
//        // some code
//        throw RuntimeException("RuntimeException in 'some code'")
//    } catch (exception: Exception) {
//        println("Handle $exception")
//    }


    //_______

//    val topLevelScope = CoroutineScope(Job())
//    topLevelScope.launch {
//        try {
//            throw RuntimeException("RuntimeException in coroutine")
//        } catch (exception: Exception) {
//            println("Handle $exception")
//        }
//    }
//    Thread.sleep(100)

    //________
//    val topLevelScope = CoroutineScope(Job())
//    topLevelScope.launch {
//        try {
//            launch {
//                throw RuntimeException("RuntimeException in nested coroutine")
//            }
//        } catch (exception: Exception) {
//            println("Handle $exception")
//        }
//    }
//    Thread.sleep(100)

    //_________

//    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("Handle $exception in CoroutineExceptionHandler ${coroutineContext}")
//    }
//
//    val topLevelScope = CoroutineScope(Job())
//    topLevelScope.launch {
//        try {
//            launch(coroutineExceptionHandler) {
//                throw RuntimeException("RuntimeException in nested coroutine")
//            }
//        } catch (exception: Exception) {
//            println("Handle $exception")
//        }
//    }
//    Thread.sleep(100)

    //__________

//    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("Handle $exception in CoroutineExceptionHandler")
//    }
//
//    val topLevelScope = CoroutineScope(Job() + coroutineExceptionHandler)
//    topLevelScope.launch(coroutineExceptionHandler) { //OR
//        try {
//            launch() {
//                throw RuntimeException("RuntimeException in nested coroutine")
//            }
//        } catch (exception: Exception) {
//            println("Handle $exception")
//        }
//    }
//    Thread.sleep(100)

    //________

//    val topLevelScope = CoroutineScope(SupervisorJob())
//
//    topLevelScope.async {
//        throw RuntimeException("RuntimeException in async coroutine")
//    }
//
//    Thread.sleep(100)
//

    //___________

//    val topLevelScope = CoroutineScope(SupervisorJob())
//
//    val deferredResult = topLevelScope.async {
//        throw RuntimeException("RuntimeException in async coroutine")
//    }
//
//    topLevelScope.launch {
//        try {
//            deferredResult.await()
//        } catch (exception: Exception) {
//            println("Handle $exception in try/catch")
//        }
//    }
//
//    Thread.sleep(100)
    //____________

//    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("Handle $exception in CoroutineExceptionHandler")
//    }
//
//    val topLevelScope = CoroutineScope(SupervisorJob() + coroutineExceptionHandler)
//    topLevelScope.launch {
//        async {
//            throw RuntimeException("RuntimeException in async coroutine")
//        }
//    }
//    Thread.sleep(100)

    //______

//    val topLevelScope = CoroutineScope(Job())
//
//    topLevelScope.launch {
//        try {
//            coroutineScope {
//                launch {
//                    throw RuntimeException("RuntimeException in nested coroutine")
//                }
//            }
//        } catch (exception: Exception) {
//            println("Handle $exception in try/catch")
//        }
//    }
//
//    Thread.sleep(100)

    //_______

//    val coroutineExceptionHandler = CoroutineExceptionHandler { coroutineContext, exception ->
//        println("Handle $exception in CoroutineExceptionHandler")
//    }
//
//    val topLevelScope = CoroutineScope(Job())
//
//    topLevelScope.launch {
//        val job1 = launch {
//            println("starting Coroutine 1")
//        }
//
//        supervisorScope {
//            val job2 = launch(coroutineExceptionHandler) {
//                println("starting Coroutine 2")
//                throw RuntimeException("Exception in Coroutine 2")
//            }
//
//            val job3 = launch {
//                println("starting Coroutine 3")
//            }
//        }
//    }
//
//    Thread.sleep(100)





}