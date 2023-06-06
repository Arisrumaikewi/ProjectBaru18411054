package com.example.projectbaru18411054

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectbaru18411054.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        val imageTitle = intss.getStringExtra("IMAGETITLE")
        val imageDesc = intss.getStringExtra("IMAGEDESC")
        val imageSrc = intss.getStringExtra("IMAGESRC")

        binding.imageTitle.text = imageTitle
        binding.imageDesc.text = imageDesc
        binding.imageDetail.loadImage(imageSrc)

    }
}