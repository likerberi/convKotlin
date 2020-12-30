package com.example.chajayo

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // normally binding is combined with data class.
        // getIntent / option & 3/4/5/6
        var detailImage : ImageView = findViewById(R.id.detail_image)
        var img1 : ImageView = findViewById(R.id.img1)
        var img2 : ImageView = findViewById(R.id.img2)
        var img3 : ImageView = findViewById(R.id.img3)
        var img4 : ImageView = findViewById(R.id.img4)

        var bundle : Bundle? = intent.extras
        var targetPath : String

        targetPath = bundle?.getString("imagePath").toString()

        Glide.with(this)
                .load(targetPath + "_option.png")
                .into(detailImage)

        GlideApp.with(this)
                .load(targetPath + "_3.jpg")
                .into(img1)
        GlideApp.with(this)
                .load(targetPath + "_4.jpg")
                .into(img2)
        GlideApp.with(this)
                .load(targetPath + "_5.jpg")
                .into(img3)
        GlideApp.with(this)
                .load(targetPath + "_6.jpg")
                .into(img4)
    }
}