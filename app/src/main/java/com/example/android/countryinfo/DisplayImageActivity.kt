package com.example.android.countryinfo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DisplayImageActivity : AppCompatActivity() {

    private lateinit var mImage : ImageView
    private val IMAGE_URL = "image_href"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_image)

        mImage = findViewById(R.id.imageview_display_image)

        if(intent.hasExtra(IMAGE_URL)){
            var imageHref = intent.getStringExtra(IMAGE_URL)

            Picasso.get()
                .load(imageHref)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(mImage)
        }

    }
}
