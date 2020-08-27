package de.devcodealpha.birthmind.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BirthdayDao {
    @Query("SELECT * from birthdays ORDER BY fullName ASC")
    fun getAlphabetizedBirthdays(): LiveData<List<Birthday>>

    @Query("SELECT * from birthdays WHERE id = :birthId")
    fun getBirthdayById(birthId: Int): LiveData<Birthday>

    @Query("SELECT * from birthdays WHERE favorite = 'true'")
    fun getFavoriteBirthdays(): LiveData<List<Birthday>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(birthday: Birthday)

    @Query("DELETE FROM birthdays")
    suspend fun deleteAll()
}