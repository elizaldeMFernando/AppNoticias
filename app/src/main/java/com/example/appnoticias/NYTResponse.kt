package com.example.appnoticias

data class NYTResponse(
    var status:String,
    var copyright:String,
    var num_results:Int,
    var results:List<Articulo>
)