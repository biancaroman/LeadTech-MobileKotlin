package com.example.leadtech_mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.Lookbook

class LookbookAdapter(
    private val onClick: (Lookbook) -> Unit
) : RecyclerView.Adapter<LookbookAdapter.LookbookViewHolder>() {

    private var lookbooks = listOf<Lookbook>()

    inner class LookbookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewLookbookName: TextView = itemView.findViewById(R.id.textViewLookbookName)

        fun bind(lookbook: Lookbook) {
            textViewLookbookName.text = lookbook.nome
            itemView.setOnClickListener {
                onClick(lookbook)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LookbookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lookbook, parent, false)
        return LookbookViewHolder(view)
    }

    override fun onBindViewHolder(holder: LookbookViewHolder, position: Int) {
        holder.bind(lookbooks[position])
    }

    override fun getItemCount(): Int = lookbooks.size

    fun submitList(newLookbooks: List<Lookbook>) {
        lookbooks = newLookbooks
        notifyDataSetChanged()
    }
}
