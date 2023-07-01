package dev.lynko.domain.models

data class Animal(
    val type: Byte,
    val name: String,
    val age: Int,
    val weight: Double,
    val id: Int = 0
) {
    companion object {
        const val TYPE_CAT: Byte = 1
        const val TYPE_DOG: Byte = 2
        const val TYPE_ANDROID: Byte = 3

    }
}