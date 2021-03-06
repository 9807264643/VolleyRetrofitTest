package com.example.flickerbrowserapp.FilckerActivity.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flickerbrowserapp.FilckerActivity.Model.Photos
import com.example.flickerbrowserapp.R
import com.squareup.picasso.Picasso


class FlickrImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var thumbnail: ImageView = view.findViewById(R.id.thumbnail)
    var title: TextView = view.findViewById(R.id.title)
}


class FlickerRecylerViewAdapter(private var photoList : List<Photos>): RecyclerView.Adapter<FlickrImageViewHolder>(){
    private val TAG = "FlickrRecyclerViewAdapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlickrImageViewHolder {
        // Called by the layout manager when it needs a new view
        Log.d(TAG, ".onCreateViewHolder new view requested")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flicker_listitems, parent, false)
        return FlickrImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlickrImageViewHolder, position: Int) {
        // Called by the layout manager when it wants new data in an existing view

//        if (photoList.isEmpty()) {
//            holder.thumbnail.setImageResource(R.drawable.img_placeholder)
//            holder.title.setText(R.string.empty_photo)
//        } else {
//            val photoItem = photoList[position]
////        Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")
//            val img = photoItem.images
//            Picasso.get().load(img)
//                .error(R.drawable.img_placeholder)
//                .placeholder(R.drawable.img_placeholder)
//                .into(holder.thumbnail)
//            holder.title.text = photoItem.title
//
//        }

        val photoItem = photoList[position]
//        Log.d(TAG, ".onBindViewHolder: ${photoItem.title} --> $position")
        val img = photoItem.images
        Picasso.get().load(img)
            .error(R.drawable.img_placeholder)
            .placeholder(R.drawable.img_placeholder)
            .into(holder.thumbnail)
        holder.title.text = photoItem.title


    }

    override fun getItemCount(): Int {
//        Log.d(TAG, ".getItemCount called")
        return if (photoList.isNotEmpty()) photoList.size else 0

//        return if(photoList.isNotEmpty()){
//            photoList.size
//        } else{
//             0
//        }

    }


    fun loadNewData(newPhotoslist: List<Photos>) {
        photoList = newPhotoslist
        notifyDataSetChanged()
    }

    fun getPhoto(position: Int): Photos? {
        return if (photoList.isNotEmpty()) photoList[position] else null
    }

}