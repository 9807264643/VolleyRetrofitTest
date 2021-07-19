package com.example.flickerbrowserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.flickerbrowserapp.VolleySliderActivity.VolleyActivityDemo

class MainActivity : AppCompatActivity() {
    lateinit var btn_vooley: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_vooley = findViewById(R.id.buttonVolley)

        btn_vooley.setOnClickListener {
            startActivity(Intent(this@MainActivity, VolleyActivityDemo::class.java))
        }

    }
}