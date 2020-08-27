package de.devcodealpha.birthmind.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.EditText
import androidx.core.view.isEmpty
import de.devcodealpha.birthmind.R

class AddBirthdayActivity : AppCompatActivity() {
    private lateinit var editFullNameView: EditText
    private lateinit var editBirthdayView: DatePicker
    private lateinit var editNoteView: EditText
    private lateinit var editCheckBox: CheckBox

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_birthday)
        editFullNameView = findViewById(R.id.edit_fullName)
        editBirthdayView = findViewById(R.id.edit_date)
        editNoteView = findViewById(R.id.edit_note)
        editCheckBox = findViewById(R.id.edit_favorite)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editFullNameView.text) && editBirthdayView.isEmpty()) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val fullName = editFullNameView.text.toString()
                val year = editBirthdayView.year
                val month = editBirthdayView.month
                val day = editBirthdayView.dayOfMonth
                val note = editNoteView.text.toString()
                val favorite = editCheckBox.isChecked

                replyIntent.putExtra(EXTRA_FULLNAME, fullName)
                replyIntent.putExtra(EXTRA_NOTE, note)
                replyIntent.putExtra(EXTRA_FAVORITE, favorite)
                replyIntent.putExtra(EXTRA_YEAR, year)
                replyIntent.putExtra(EXTRA_MONTH, month)
                replyIntent.putExtra(EXTRA_DAY, day)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_FULLNAME = "de.devcodealpha.birthmind.REPLY.FULLNAME"
        const val EXTRA_NOTE = "de.devcodealpha.birthmind.REPLY.NOTE"
        const val EXTRA_FAVORITE = "de.devcodealpha.birthmind.REPLY.FAVORITE"
        const val EXTRA_YEAR = "de.devcodealpha.birthmind.REPLY.YEAR"
        const val EXTRA_MONTH = "de.devcodealpha.birthmind.REPLY.MONTH"
        const val EXTRA_DAY = "de.devcodealpha.birthmind.REPLY.DAY"
    }
}