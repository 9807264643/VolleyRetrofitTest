package com.example.flickerbrowserapp.FilckerActivity.Model

import android.util.Log
import java.io.IOException
import java.io.ObjectStreamException
import java.io.Serializable
import kotlin.jvm.Throws

class Photos(var title: String,var author: String,var authorId: String,var links: String, var tags: String,
             var images: String): Serializable {

    companion object{
        private const val serialVersionUID = 1L
    }

    override fun toString(): String {
        return "Photos(title='$title', author='$author', authorId='$authorId', links='$links', tags='$tags', images='$images')"
    }

    // when use for itemRecyclerClickListner for itemLongClick
    @Throws(IOException::class)
    private fun writeObject(out: java.io.ObjectOutputStream){
        Log.d("photo", "writeObject cilled")
        out.writeUTF(title)
        out.writeUTF(author)
        out.writeUTF(authorId)
        out.writeUTF(links)
        out.writeUTF(tags)
        out.writeUTF(images)
    }

    @Throws(IOException::class, ClassNotFoundException::class)
    private fun readObject(inStream: java.io.ObjectInputStream){
        Log.d("photo", "readObject cilled")
        title = inStream.readUTF()
        author = inStream.readUTF()
        authorId = inStream.readUTF()
        links = inStream.readUTF()
        tags = inStream.readUTF()
        images = inStream.readUTF()

    }

    @Throws(ObjectStreamException::class)
    private fun readObjectNoData(){
        Log.d("photo", "readObjectNoData cilled")
    }

}