package dev.lynko.cources2023.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import dev.lynko.cources2023.database.dao.AnimalsDao
import dev.lynko.cources2023.model.Animal

@Database(
    entities = [Animal::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun animalsDao(): AnimalsDao

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "my_animals"

        private var INSTANCE: AppDatabase? = null

        fun getDatabase(
            context: Context
        ): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}