package dev.lynko.cources2023.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.lynko.cources2023.database.dao.AnimalsDao
import dev.lynko.cources2023.model.Animal

@Database(
    entities = [Animal::class],
    version = AppDatabase.DATABASE_VERSION
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun animalsDao(): AnimalsDao

    companion object {

        const val DATABASE_VERSION = 2
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
                ).addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE animal ADD COLUMN description123 TEXT NOT NULL DEFAULT ''")
            }
        }

    }
}