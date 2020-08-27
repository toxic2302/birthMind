package de.devcodealpha.birthmind.ui.birthdaylist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import de.devcodealpha.birthmind.R
import de.devcodealpha.birthmind.adapter.BirthdaylistAdapter
import de.devcodealpha.birthmind.database.Birthday
import de.devcodealpha.birthmind.ui.AddBirthdayActivity
import kotlinx.android.synthetic.main.activity_add_birthday.*
import java.time.LocalDate
import java.util.*

class BirthdayListFragment : Fragment() {
    private lateinit var birthdaylistViewModel: BirthdaylistViewModel
    private val newBirthdayActivityRequestCode = 1

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        birthdaylistViewModel = ViewModelProvider(this).get(BirthdaylistViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_birthday_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = BirthdaylistAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        birthdaylistViewModel.allBirthdays().observe(viewLifecycleOwner, Observer { birthdays ->
            birthdays?.let { adapter.setBirthdays(it) }
        })
        val fab = root.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(requireContext(), AddBirthdayActivity::class.java)
            startActivityForResult(intent, newBirthdayActivityRequestCode)
        }
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newBirthdayActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val birthday = Birthday()
            data?.getStringExtra(AddBirthdayActivity.EXTRA_FULLNAME)?.let {
                birthday.fullName = it
            }
            data?.getStringExtra(AddBirthdayActivity.EXTRA_NOTE)?.let {
                birthday.note = it
            }
            data?.getBooleanExtra(AddBirthdayActivity.EXTRA_FAVORITE, false)?.let {
                birthday.favorite = it
            }
            data?.getIntExtra(AddBirthdayActivity.EXTRA_YEAR, -1)?.let {
                birthday.year = it
            }
            data?.getIntExtra(AddBirthdayActivity.EXTRA_MONTH, -1)?.let {
                birthday.month = it
            }
            data?.getIntExtra(AddBirthdayActivity.EXTRA_DAY, -1)?.let {
                birthday.day = it
            }
        } else {
            Toast.makeText(
                requireContext(),
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}