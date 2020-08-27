package de.devcodealpha.birthmind.ui.birthdaylist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import de.devcodealpha.birthmind.database.Birthday
import de.devcodealpha.birthmind.database.BirthdayRepository
import de.devcodealpha.birthmind.database.BirthdayRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BirthdaylistViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BirthdayRepository
    private val allBirthdays: LiveData<List<Birthday>>

    init {
        val birthdayDao = BirthdayRoomDatabase.getDatabase(application, viewModelScope).birthdayDao()
        repository = BirthdayRepository(birthdayDao)
        allBirthdays = repository.allBirthdays
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(birthday: Birthday) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(birthday)
    }

    fun allBirthdays(): LiveData<List<Birthday>> {
        return repository.allBirthdays
    }
}