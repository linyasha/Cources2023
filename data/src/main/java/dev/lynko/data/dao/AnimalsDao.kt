package dev.lynko.data.dao


import androidx.room.*
import dev.lynko.data.model.AnimalModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalsDao {

    @Query("SELECT * FROM animal")
    fun getAll(): List<AnimalModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animalModel: AnimalModel)

    @Query("SELECT * FROM animal")
    fun getAllFlow(): Flow<List<AnimalModel>>

    @Query("UPDATE animal SET weight = :newWeight WHERE id = :id")
    fun updateWeight(id: Int, newWeight: Double)

    @Query("DELETE FROM animal WHERE id = :id")
    fun deleteAnimal(id: Int)

}