package com.example.mjmcatclicker

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InsructionPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_insruction_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val instuctionMusic = MediaPlayer.create(this, R.raw.sneakyadventuremacleod)
        instuctionMusic.start()

        val buttonClick = findViewById<Button>(R.id.goBackButton)
        buttonClick.setOnClickListener {
            instuctionMusic.stop()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}