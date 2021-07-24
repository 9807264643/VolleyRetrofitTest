package com.example.flickerbrowserapp.FilckerActivity.DownloadJsonByAsyc

import android.os.AsyncTask
import android.util.Log
import com.example.flickerbrowserapp.FilckerActivity.Model.Photos
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception

@Suppress("DEPRECATION")
class GetFlikrJsonData(private val listner : OnDataAvialable) : AsyncTask<String, Void, ArrayList<Photos>>() {

    private val TAG = "FlickerActivity"

    interface OnDataAvialable{
        fun onDataAvailables(data: List<Photos>)
        fun onError(exception: Exception)

    }

    override fun doInBackground(vararg params: String): ArrayList<Photos> {
      Log.d(TAG, "doInBackground starts")

        val photosList = ArrayList<Photos>()

        try {
            val jsonObject = JSONObject(params[0])
            val jsonArray = jsonObject.getJSONArray("items")

            for (i in 0 until jsonArray.length()){

                val jsonPhotos = jsonArray.getJSONObject(i)

                val title = jsonPhotos.getString("title")
                val author = jsonPhotos.getString("author")
                val authorId = jsonPhotos.getString("author_id")
                val tags = jsonPhotos.getString("tags")

                val jsonMedia = jsonPhotos.getJSONObject("media")
                val photoUrl = jsonMedia.getString("m")
                val links = photoUrl.replaceFirst("-m.jpg","-b.jpg")

                val photosModel = Photos(title, author, authorId, links, tags, photoUrl)
                photosList.add(photosModel)
                Log.d(TAG, "doInBackground $photosModel")

            }

        }catch (e: JSONException){
            e.printStackTrace()
            Log.d(TAG, "doinbackgroungd: error processing ${e.message}")
            cancel(true)
            listner.onError(e)
        }

        return photosList
    }

    override fun onPostExecute(result: ArrayList<Photos>) {
        Log.d(TAG, "onPostExecute starts")
        super.onPostExecute(result)
        listner.onDataAvailables(result)
        Log.d(TAG, "onPostExecute ends")
    }
}