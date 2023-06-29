package dev.lynko.cources2023.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "animal"
)
data class Animal(
    val type: Byte,
    val name: String,
    val age: Int,
//    @ColumnInfo(name = "w")
    val weight: Double,
    val description: String,
    val createdAt: Long,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {

    companion object {
        const val TYPE_CAT: Byte = 1
        const val TYPE_DOG: Byte = 2
        const val TYPE_ANDROID: Byte = 3

    }
}
