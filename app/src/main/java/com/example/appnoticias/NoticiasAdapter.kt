package com.example.appnoticias

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoticiasAdapter(val noticias:List<Articulo>, val listener: OnItemClickListener):RecyclerView.Adapter<NoticiasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiasViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return NoticiasViewHolder(layoutInflater.inflate(R.layout.item_noticia,parent,false),listener)
    }

    override fun onBindViewHolder(holder: NoticiasViewHolder, position: Int) {
        holder.render(noticias[position])
    }

    override fun getItemCount(): Int {
        return noticias.size
    }
}