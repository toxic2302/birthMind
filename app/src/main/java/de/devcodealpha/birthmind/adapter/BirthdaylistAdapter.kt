package de.devcodealpha.birthmind.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.devcodealpha.birthmind.R
import de.devcodealpha.birthmind.database.Birthday

class BirthdaylistAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<BirthdaylistAdapter.BirthdayViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var birthdays = emptyList<Birthday>() // Cached copy of words

    inner class BirthdayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val birthdayItemView: TextView = itemView.findViewById(R.id.birthdayView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BirthdayViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return BirthdayViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BirthdayViewHolder, position: Int) {
        val current = birthdays[position]
        holder.birthdayItemView.text = current.fullName
    }

    internal fun setBirthdays(birthdays: List<Birthday>) {
        this.birthdays = birthdays
        notifyDataSetChanged()
    }

    override fun getItemCount() = birthdays.size
}