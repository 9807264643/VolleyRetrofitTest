package com.example.flickerbrowserapp.FilckerActivity

import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

/**
 * Created by timbuchalka for Android Oreo with Kotlin course
 * from www.learnprogramming.academy
 */

enum class DownloadStatus {
    OK, IDLE, NOT_INITIALISED, FAILED_OR_EMPTY, PERMISSIONS_ERROR, ERROR
}

@Suppress("DEPRECATION")
class GetRawData(private val listner: OnDownloadCompleteListner) : AsyncTask<String, Void, String>() {
    private val TAG = "FlickerActivity"
    private var downloadStatus = DownloadStatus.IDLE

    interface OnDownloadCompleteListner{
        fun onDownloadComplete(data: String, status: DownloadStatus)
    }

//    private var listner: Flicker? = null
//    fun setDownloadCompleteListner(callBackObject: Flicker){
//        listner = callBackObject
//    }


    override fun onPostExecute(result: String) {
//        Log.d(TAG, "onPostExecute called, parameter is $result")
        Log.d(TAG, "onPostExecute called")

        listner.onDownloadComplete(result, downloadStatus)
    }


    override fun doInBackground(vararg params: String?): String {
        Log.d(TAG, "doInBackground called")
        if (params[0] == null) {
            downloadStatus = DownloadStatus.NOT_INITIALISED
            return "No URL specified"
        }

        try {
            downloadStatus = DownloadStatus.OK
            Log.d(TAG, "downloadStatus ok called")
            return URL(params[0]).readText()

        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> {
                    downloadStatus = DownloadStatus.NOT_INITIALISED
                    "doInBackground: Invalid URL ${e.message}"
                }
                is IOException -> {
                    downloadStatus = DownloadStatus.FAILED_OR_EMPTY
                    "doInBackground: IO Exception reading data: ${e.message}"
                }
                is SecurityException -> {
                    downloadStatus = DownloadStatus.PERMISSIONS_ERROR
                    "doInBackground: Security exception: Needs permission? ${e.message}"
                }
                else -> {
                    downloadStatus = DownloadStatus.ERROR
                    "Unknown error: ${e.message}"
                }
            }
            Log.e(TAG, errorMessage)

            return errorMessage
        }

    }
}