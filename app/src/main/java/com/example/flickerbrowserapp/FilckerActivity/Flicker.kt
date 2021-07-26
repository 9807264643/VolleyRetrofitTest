package com.example.flickerbrowserapp.FilckerActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerbrowserapp.BaseActivity
import com.example.flickerbrowserapp.FLICKR_QUERY
import com.example.flickerbrowserapp.FilckerActivity.Adapter.FlickerRecylerViewAdapter
import com.example.flickerbrowserapp.FilckerActivity.DownloadJsonByAsyc.GetFlikrJsonData
import com.example.flickerbrowserapp.FilckerActivity.Model.Photos
import com.example.flickerbrowserapp.PHOTO_TRANSFER
import com.example.flickerbrowserapp.PhotosDetailActivity.PhotosDetail
import com.example.flickerbrowserapp.R
import com.example.flickerbrowserapp.SearchActivity.Search
import java.lang.Exception

@Suppress("DEPRECATION")
class Flicker : BaseActivity(), GetRawData.OnDownloadCompleteListner,
    GetFlikrJsonData.OnDataAvialable, RecyclerOnItemClicklistner.OnRecyclerClickListner {
    private val TAG = "FlickerActivity"

    private val flickerRecylerViewAdapter = FlickerRecylerViewAdapter(ArrayList())
    lateinit var recycler_flick : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flicker2)
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
        activateToolbar(false)

        recycler_flick = findViewById(R.id.recycler_fliker)
         recycler_flick.layoutManager = LinearLayoutManager(this)
        recycler_flick.addOnItemTouchListener(RecyclerOnItemClicklistner(this, recycler_flick, this))
        recycler_flick.adapter = flickerRecylerViewAdapter

//        val url = createUri("https://api.flickr.com/services/feeds/photos_public.gne", "android,oreo", "en-us", true)
//        val getRawData = GetRawData(this)
//        getRawData.setDownloadCompleteListner(this)
//        getRawData.execute("https://api.flickr.com/services/feeds/photos_public.gne?tags=android,oreo&format=json&nojsoncallback=1")
//        getRawData.execute(url)


    }


    private fun createUri(baseUrl: String, creatSearch: String, lang: String, allMatch: Boolean): String {
        Log.d(TAG, "createUri starts")

       return Uri.parse(baseUrl).
            buildUpon().
            appendQueryParameter("tags", creatSearch).
            appendQueryParameter("tagmode", if (allMatch) "ALL" else "ANY").
            appendQueryParameter("lang", lang).
            appendQueryParameter("format", "json").
            appendQueryParameter("nojsoncallback", "1").
            build().toString()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        Log.d(TAG, "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d(TAG, "onOptionsItemSelected called")
        return when (item.itemId) {
            R.id.action_search -> {
                startActivity(Intent(this@Flicker, Search::class.java))
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called")
            val getFlikrJsonData = GetFlikrJsonData(this)
            getFlikrJsonData.execute(data)

        } else {
            // download failed
            Log.d(TAG, "onDownloadCompleted failed with status $status. Error message is: $data")
        }
    }

    override fun onDataAvailables(data: List<Photos>) {
        Log.d(TAG, "onDataAvailables $data")
        flickerRecylerViewAdapter.loadNewData(data)

    }

    override fun onError(exception: Exception) {
        Log.d(TAG, "onError $exception")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, "OnItemClick: starts")
        Toast.makeText(this, "normal tap at position $position", Toast.LENGTH_LONG).show()

    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG, "OnItemLongClick: starts")
//        Toast.makeText(this, "Long tap at position $position", Toast.LENGTH_LONG).show()

        val photo = flickerRecylerViewAdapter.getPhoto(position)
        if (photo != null) {
            val intent = Intent(this, PhotosDetail::class.java)
            intent.putExtra(PHOTO_TRANSFER, photo)
            startActivity(intent)
        }
    }


    override fun onResume() {
        Log.d(TAG, ".onResume starts")
        super.onResume()

        val sharedPref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val queryResult = sharedPref.getString(FLICKR_QUERY, "")

        if (queryResult!!.isNotEmpty()) {
            val url = createUri(
                "https://api.flickr.com/services/feeds/photos_public.gne",
                queryResult!!,
                "en-us",
                true
            )
            val getRawData = GetRawData(this)
            getRawData.execute(url)
        }
        Log.d(TAG, ".onResume: ends")
    }


}