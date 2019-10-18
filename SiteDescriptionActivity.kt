package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.View
import android.widget.Gallery
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_site_desc.*
import java.io.*
import java.util.*

class SiteDescriptionActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        private val GALLERY = 1
        private val IMG_DIR = "sample"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_desc)



        thumbLeft.setOnClickListener(this)
        thumbRight.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val id = v.id

        when(id) {
            R.id.thumbLeft, R.id.thumbRight -> choosePhotoFromGallery()
        }
    }

    fun choosePhotoFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ( requestCode == GALLERY) {
            if ( data != null) {
                val contentURI = data!!.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                    val path = saveImg(bitmap)
                    Toast.makeText(this@SiteDescriptionActivity, "Image loaded", Toast.LENGTH_SHORT).show()
                    //if(thumbLeft.)
                    thumbLeft!!.setImageBitmap(bitmap)
                    // "2"
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        // else // vacancy for CAMERA
    }

    private fun saveImg(bitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
            (Environment.getExternalStorageDirectory()).toString() + IMG_DIR)
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }

        try
        {
            val f = File(wallpaperDirectory, ((Calendar.getInstance()
                .getTimeInMillis()).toString() + ".jpg"))
            f.createNewFile()
            val fo = FileOutputStream(f)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(this,
                arrayOf(f.getPath()),
                arrayOf("image/jpeg"), null)
            fo.close()
            return f.getAbsolutePath()
        }
        catch (e1: IOException) {
            e1.printStackTrace()
        }
        return ""
    }
}