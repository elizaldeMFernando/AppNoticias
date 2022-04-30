package com.example.appnoticias

data class Articulo(
    var url:String,
    var section:String,
    var title:String,
    var published_date:String,
    var media:List<ArticuloMedia>
)