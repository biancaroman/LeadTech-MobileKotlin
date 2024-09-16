package com.example.leadtech_mobile.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leadtech_mobile.R
import com.example.leadtech_mobile.model.PecaRoupa

class PecaRoupaAdapter : RecyclerView.Adapter<PecaRoupaAdapter.PecaViewHolder>() {

    private var pecasList: List<PecaRoupa> = listOf()

    fun submitList(pecas: List<PecaRoupa>?) {
        pecasList = pecas ?: emptyList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PecaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_peca_roupa, parent, false)
        return PecaViewHolder(view)
    }

    override fun onBindViewHolder(holder: PecaViewHolder, position: Int) {
        holder.bind(pecasList[position])
    }

    override fun getItemCount(): Int = pecasList.size

    inner class PecaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewNomePeca: TextView = itemView.findViewById(R.id.textViewNomePeca)
        private val textViewCategoria: TextView = itemView.findViewById(R.id.textViewCategoria)

        fun bind(peca: PecaRoupa) {
            textViewNomePeca.text = peca.nome
            textViewCategoria.text = peca.categoria.name
        }
    }
}
