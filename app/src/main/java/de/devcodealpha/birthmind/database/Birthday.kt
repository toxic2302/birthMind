package de.devcodealpha.birthmind.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "birthdays")
data class Birthday(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "fullName") var fullName: String = "",
    @ColumnInfo(name ="year") var year: Int = 0,
    @ColumnInfo(name ="month") var month: Int = 0,
    @ColumnInfo(name ="day") var day: Int = 0,
    @ColumnInfo(name = "note") var note: String = "",
    @ColumnInfo(name = "favorite") var favorite: Boolean? = false
)
