package com.example.spacefinder

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class TestHolder : AppCompatActivity() {

    private lateinit var database: DatabaseReference// ...

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        var holder = arrayListOf<Something>()
        database = FirebaseDatabase.getInstance().reference	// ++ path_config :: .child("path")
        val postListener = object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //
            }

            override fun onDataChange(p0: DataSnapshot) {
                for (datasnapshot in p0.children) {
                    val post = datasnapshot.getValue(Something::class.java)
                    if (post != null) {
                        holder.add(post)
                    }
                }
            }

            // for single
            override fun onDataChange(p0: DataSnapshot) {
                val post = p0.getValue(Something::class.java)
                if (post != null) {
                    holder.add(post)
                }
            }
        }
        database.addValueEventListener(postListener)

    }

}