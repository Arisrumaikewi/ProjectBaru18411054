package com.example.projectbaru18411054

import com.google.firebase.database.Exclude
data class Image (
    var imageTitle :String? = null,
    var imageDesc :String? = null,
    var imageSrc :String? = null,

    @get:Exclude
    @set:Exclude
    var key:String? = null
)
