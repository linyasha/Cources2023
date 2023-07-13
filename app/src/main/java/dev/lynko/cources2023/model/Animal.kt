package dev.lynko.cources2023.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "animal"
)
data class   Animal(
    val type: Byte,
    val name: String,
    val age: Int,
//    @ColumnInfo(name = "w")
    val weight: Double,
   val description: String,
    val createdAt: Long = System.currentTimeMillis(),
    //("Добавьте поле description, которое будет содержать описание питомца")
    //("Добавьте поле createdAt, которое будет равно времени создания питомца(System.currentTimeMillis)")
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    companion object {
        const val TYPE_CAT: Byte = 1
        const val TYPE_DOG: Byte = 2
        const val TYPE_HAMSTER: Byte = 3
        const val TYPE_FISH: Byte= 4
        const val TYPE_BIRD: Byte = 5

    }
}
