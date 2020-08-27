package de.devcodealpha.birthmind.database

import androidx.lifecycle.LiveData

class BirthdayRepository(private val birthdayDao: BirthdayDao) {

    val allBirthdays: LiveData<List<Birthday>> = birthdayDao.getAlphabetizedBirthdays()

    suspend fun insert(birthday: Birthday) {
        birthdayDao.insert(birthday)
    }
}