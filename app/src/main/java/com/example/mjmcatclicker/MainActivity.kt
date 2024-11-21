package com.example.mjmcatclicker

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val titleMusic = MediaPlayer.create(this, R.raw.monkeysspinningmonkeysmacleod)
        titleMusic.start()

        // Button to start the game!
        val buttonClickGame = findViewById<Button>(R.id.startBtn)
        buttonClickGame.setOnClickListener {
            titleMusic.stop()
            println("USER entered GameActivity")
            val intent = Intent(this,GameActivity::class.java)
            startActivity(intent)
        }

        // Button to view instruction page!
        val buttonClick = findViewById<Button>(R.id.instBtn)
        buttonClick.setOnClickListener {
            titleMusic.stop()
            println("USER entered InstructionPage")
            val intent = Intent(this,InsructionPage::class.java)
            startActivity(intent)
        }

    }
}