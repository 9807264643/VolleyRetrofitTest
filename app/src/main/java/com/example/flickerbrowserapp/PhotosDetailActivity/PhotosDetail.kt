package com.example.flickerbrowserapp.PhotosDetailActivity

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.flickerbrowserapp.BaseActivity
import com.example.flickerbrowserapp.FilckerActivity.Model.Photos
import com.example.flickerbrowserapp.PHOTO_TRANSFER
import com.example.flickerbrowserapp.R
import com.squareup.picasso.Picasso

class PhotosDetail : BaseActivity() {
    lateinit var photo_title: TextView
    lateinit var photo_author: TextView
    lateinit var photo_tags: TextView
    lateinit var photo_image: ImageView

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos_detail)
        activateToolbar(true)
            photo_title = findViewById(R.id.photo_title)
            photo_author = findViewById(R.id.photo_author)
            photo_tags = findViewById(R.id.photo_tags)
            photo_image = findViewById(R.id.photo_image)

        val photo = intent.getSerializableExtra(PHOTO_TRANSFER) as Photos

//            photo_title.text = photo.title
            photo_title.text = resources.getString(R.string.photo_tilte_text, photo_title)

            photo_author.text = photo.author
            photo_tags.text = photo.tags

            Picasso.get().load(photo.links)
                .error(R.drawable.img_placeholder)
                .placeholder(R.drawable.img_placeholder)
                .into(photo_image)


    }
}