package de.devcodealpha.birthmind.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@Database(entities = arrayOf(Birthday::class), version = 1, exportSchema = false)
abstract class BirthdayRoomDatabase : RoomDatabase() {
    abstract fun birthdayDao(): BirthdayDao

    companion object {
        @Volatile
        private var INSTANCE: BirthdayRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BirthdayRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BirthdayRoomDatabase::class.java,
                    "birthday_database"
                ).addCallback(BirthdayDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class BirthdayDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.birthdayDao())
                }
            }
        }

        suspend fun populateDatabase(birthdayDao: BirthdayDao) {
            // Delete all content here.
            birthdayDao.deleteAll()

            // Add sample birthdays.
            var birthday = Birthday(fullName = "John Doe", year = 1960, month = 3, day = 1, note = "Note", favorite = true)
            birthdayDao.insert(birthday)
            birthday = Birthday(fullName = "Jane Doe", year = 1990, month = 4, day = 20, note = "Note", favorite = false)
            birthdayDao.insert(birthday)
        }
    }
}