package com.example.flickerbrowserapp.SearchActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.example.flickerbrowserapp.BaseActivity
import com.example.flickerbrowserapp.R

class Search : BaseActivity() {
    private val TAG = "SearchActivity"

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
//        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        activateToolbar(true)
        Log.d(TAG, ".onCreate: ends")
    }
}