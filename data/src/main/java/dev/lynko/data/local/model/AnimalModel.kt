package dev.lynko.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.lynko.domain.models.Animal

@Entity(
    tableName = "animal"
)
data class AnimalModel(
    val type: Byte,
    val name: String,
    val age: Int,
//    @ColumnInfo(name = "w")
    val weight: Double,
    //TODO("Добавьте поле description, которое будет содержать описание питомца")
    //TODO("Добавьте поле createdAt, которое будет равно времени создания питомца(System.currentTimeMillis)")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    fun toAnimal(): Animal = Animal(
        type,
        name,
        age,
        weight
    )

    companion object {
        const val TYPE_CAT: Byte = 1
        const val TYPE_DOG: Byte = 2
        const val TYPE_ANDROID: Byte = 3

        fun map(animal: Animal): AnimalModel = AnimalModel(animal.type, animal.name, animal.age, animal.weight)

    }
}
