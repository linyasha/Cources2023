package dev.lynko.cources2023

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.DoubleAdder

//SingleTon

object SingleTon {
    @JvmField
    val a = "Hello"
}

//fun main() {
//    println(SingleTon.a)
//}

//_____________________


//class FoodOrder private constructor(
//    val id: Int?,
//    val name: String?,
//    val address: String?
//) {
//
//    data class Builder(
//        private var id: Int? = null,
//        private var name: String? = null,
//        private var address: String? = null
//    ) {
//        fun id(id: Int?) = apply { this.id = id }
//        fun name(name: String?) = apply { this.name = name }
//        fun address(address: String?) = apply { this.address = address }
//
//        fun build() = FoodOrder(id, name, address)
//    }
//
//}

//interface  AddressDeliveryBuilder {
//
//    fun address(address: String?): AddressDeliveryBuilder
//
//}

//class FoodOrder private constructor(
//    val id: Int?,
//    val name: String?,
//    val address: String?
//) {
//
//    data class Builder(
//        private var id: Int? = null,
//        private var name: String? = null,
//        private var address: String? = null
//    ): AddressDeliveryBuilder  {
//        fun id(id: Int?) = apply { this.id = id }
//        fun name(name: String?) = apply { this.name = name }
//        override fun address(address: String?) = apply { this.address = address }
//
//        fun build() = FoodOrder(id, name, address)
//    }
//}
//
//class Director(val builder: AddressDeliveryBuilder) {
//    fun construct() {
//        when (builder) {
//            is FoodOrder.Builder -> {
//                builder.address("Some Food address")
//            }
//            else -> {
//                builder.address("Some address")
//            }
//        }
//    }
//}
//
//fun main() {
//    val foodOrderBuilder = FoodOrder.Builder()
//        .id(12)
//        .name("Artur")
//    Director(foodOrderBuilder).construct()
//    foodOrderBuilder.build()
//}

//_____________________

//interface Task {  //Product
//    fun execute()
//}
//
//class SendMessageTask(): Task {  //Concrete Product
//    override fun execute() {
//       println("Some message")
//    }
//}
//
//abstract class Executor {  //Creator
//    fun executeInBackground() {
//        val task = getTask()
//        GlobalScope.launch(Dispatchers.IO) {
//            task.execute()
//        }
//    }
//    abstract fun getTask(): Task
//}
//
//class SendMessageExecutor: Executor() {  //Concrete creator
//
//    override fun getTask(): Task {
//        return SendMessageTask()
//    }
//}
//
//fun main() {
//    val sendMessageExecutor = SendMessageExecutor()
//    sendMessageExecutor.executeInBackground()
//}




