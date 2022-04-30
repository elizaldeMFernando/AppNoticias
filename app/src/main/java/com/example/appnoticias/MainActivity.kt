package com.example.appnoticias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var adapter:NoticiasAdapter
    private val noticias= mutableListOf<Articulo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<NYTResponse> = getRetrofit().create(APIService::class.java).getMostPopular("7.json?api-key=f0PNAK79UFGARYxhI5RGcVjpYvSnJD7w")
            val nYTResponse:NYTResponse?=call.body()
            runOnUiThread {
                if(call.isSuccessful){
                    val articulos:List<Articulo> = nYTResponse?.results ?: emptyList()
                    if (!articulos.isEmpty()){
                        noticias.clear()
                        noticias.addAll(articulos)
                        adapter.notifyDataSetChanged()
                        //findViewById<TextView>(R.id.textView).text=articulos[0].media[0].medias[0].format
                        findViewById<WebView>(R.id.browser1).loadUrl(articulos[0].url)
                    }
                }
            }
        }

    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            //https://api.nytimes.com/svc/mostpopular/v2/emailed/7.json?api-key=f0PNAK79UFGARYxhI5RGcVjpYvSnJD7w
            .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/emailed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecyclerView(){
        adapter = NoticiasAdapter(noticias,this)

        findViewById<RecyclerView>(R.id.barraNoticias).layoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)

        findViewById<RecyclerView>(R.id.barraNoticias).adapter=adapter
        //binding.rvDogs.layoutManager=LinearLayoutManager(this)
        // binding.rvDogs.adapter = adapter
    }

    override fun onItemClick(noticia: Articulo) {
        findViewById<WebView>(R.id.browser1).loadUrl(noticia.url)
    }
}