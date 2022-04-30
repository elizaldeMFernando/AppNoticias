package com.example.appnoticias

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NoticiasViewHolder(val view:View,val listener: OnItemClickListener):RecyclerView.ViewHolder(view) {

    fun render(noticia:Articulo){
        view.findViewById<TextView>(R.id.txtTitle).text = noticia.title
        view.findViewById<TextView>(R.id.txtDate).text = noticia.published_date

        Picasso.get().load(noticia.media[0].medias[0].url).into(view.findViewById<ImageView>(R.id.thumbImage))
        view.findViewById<ConstraintLayout>(R.id.itemView).setOnClickListener {
            listener.onItemClick(noticia)
        }


    }
}