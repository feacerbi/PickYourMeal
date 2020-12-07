package com.felipeacerbi.pickyourmeal.view

import android.text.format.DateFormat
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.felipeacerbi.pickyourmeal.databinding.DateListItemBinding
import com.felipeacerbi.pickyourmeal.util.inflater
import java.util.*

class DateAdapter : RecyclerView.Adapter<DateAdapter.DateViewHolder>() {

    private var currentDate: Calendar = Calendar.getInstance()

    fun update(date: Calendar) {
        currentDate = date
        notifyItemChanged(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder =
        DateViewHolder(
            DateListItemBinding.inflate(parent.inflater(), parent, false)
        )

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 1

    inner class DateViewHolder(
        private val binding: DateListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() = with (binding) {
            date.text = DateFormat.format("dd MMM", currentDate).toString()
        }
    }
}