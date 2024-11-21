package com.example.mjmcatclicker

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Variables
        var gameStarted = 0
        var gameLevel = 0
        var playerScore = 0
        var hitboxOn = 0
        val startButton = findViewById<Button>(R.id.startGameButton)
        val catPress = findViewById<Button>(R.id.catButton)
        val hitboxButton = findViewById<Button>(R.id.hitboxBtn)
        val hitboxMessage = findViewById<TextView>(R.id.hitboxWon)
        val levelImage = findViewById<ImageView>(R.id.levelImage)
        val chronoTimer = findViewById<Chronometer>(R.id.chronometer)
        val catSE1 = MediaPlayer.create(this, R.raw.catsound1)
        val catSE2 = MediaPlayer.create(this, R.raw.catsound2)
        val catSE3 = MediaPlayer.create(this, R.raw.catsound3)
        val catSE4 = MediaPlayer.create(this, R.raw.catsound4)
        val catSE5 = MediaPlayer.create(this, R.raw.catsound5)
        val musicBG = MediaPlayer.create(this, R.raw.thinkingmusickmacleod)
        val ambianceBG = MediaPlayer.create(this, R.raw.heartofnowheremacleod)

        // Setting default values for button
        catPress.setTextSize(12F)
        catPress.setX(0F)
        catPress.setY(0F)

        // Function for loading the correct level
        fun levelLoad(a: Int) {
            // Checking and setting up level!
            // Example: if level X, Change BACKGROUND and move button to right LOCATION
            if (gameLevel == 1) {
                levelImage.setImageResource(R.drawable.piclevel1)
                catPress.setTextSize(100F)
                catPress.setX(160F)
                catPress.setY(750F)
            } else if (gameLevel == 2) {
                levelImage.setImageResource(R.drawable.piclevel2)
                catPress.setTextSize(12F)
                catPress.setX(425F)
                catPress.setY(500F)
            } else if (gameLevel == 3) {
                levelImage.setImageResource(R.drawable.piclevel3)
                catPress.setTextSize(46F)
                catPress.setX(75F)
                catPress.setY(550F)
            } else if (gameLevel == 4) {
                levelImage.setImageResource(R.drawable.piclevel4)
                catPress.setTextSize(8F)
                catPress.setX(720F)
                catPress.setY(1250F)
            } else if (gameLevel == 5) {
                levelImage.setImageResource(R.drawable.piclevel5)
                catPress.setTextSize(40F)
                catPress.setX(100F)
                catPress.setY(1320F)
            } else if (gameLevel == 6) {
                levelImage.setImageResource(R.drawable.piclevel6)
                catPress.setTextSize(16F)
                catPress.setX(100F)
                catPress.setY(1250F)
            } else if (gameLevel == 7) {
                levelImage.setImageResource(R.drawable.piclevel7)
                catPress.setTextSize(4F)
                catPress.setX(680F)
                catPress.setY(1100F)
            } else if (gameLevel == 8) {
                levelImage.setImageResource(R.drawable.piclevel8)
                catPress.setTextSize(2F)
                catPress.setX(650F)
                catPress.setY(520F)
            } else if (gameLevel == 9) {
                levelImage.setImageResource(R.drawable.piclevel9)
                catPress.setTextSize(2F)
                catPress.setX(500F)
                catPress.setY(1150F)
            } else if (gameLevel == 10) {
                levelImage.setImageResource(R.drawable.piclevel10)
                catPress.setTextSize(100F)
                catPress.setX(80F)
                catPress.setY(400F)
            }// ADD MORE LEVELS!!!!!!!!!!!!!!!!!!!!!!!!!!
        }

        // Decides which the next sound effect is going to be
        fun soundEffectFunction(a: Int) {
            if (a == 1) {
                catSE1.start()
            } else if (a == 2) {
                catSE2.start()
            } else if (a == 3) {
                catSE3.start()
            } else if (a == 4) {
                catSE4.start()
            } else if (a == 5) {
                catSE5.start()
            }
        }

        // Function for running the game
        // a = Is the game started? 0 or 1
        // b = Which level are you playing?
        // c = Player's Score? Return this
        fun gameFunction(a: Int, b: Int, c: Int): Int {
            println("Game Function is running!")

            // When you tap on a CAT
            catPress.setOnClickListener {

                // Default Option when pressing a cat
                println("Cat Found!")
                playerScore += 1
                println("Score:")
                println(playerScore)

                // Play sound effect
                val nextSE = (1..5).random()
                soundEffectFunction(nextSE)

                val randomNumbers = (1..10).random()
                gameLevel = randomNumbers
                println("Level:")
                println(gameLevel)

                // Setting default values for button
                catPress.setTextSize(12F)
                catPress.setX(0F)
                catPress.setY(0F)

                // Run levelLoad Function, setting up level!
                levelLoad(gameLevel)

                // When player has reached 10 Score, the game ends!
                if (playerScore == 10) {
                    // Switch music
                    musicBG.stop()
                    ambianceBG.start()

                    // Switch background picture
                    levelImage.setImageResource(R.drawable.picwinscreen)

                    // Stop and show chronoTimer
                    chronoTimer.stop()
                    chronoTimer.setTextColor(Color.BLACK)

                    // If game is won with hitboxes on, a message will indicate it
                    if (hitboxOn == 1) {
                        catPress.setVisibility(View.INVISIBLE)
                        println("YOU WON!")
                        hitboxMessage.setTextColor(Color.parseColor("#000000"))
                    } else {
                        catPress.setVisibility(View.INVISIBLE)
                        println("YOU WON!")
                    }
                }
            }

            return c
        }

        // Code runs when you press on the start button
        startButton.setOnClickListener {
            gameStarted = 1

            // Starts the chronometer
            chronoTimer.start()

            // Starts background music
            musicBG.start()

            // Default Option when pressing a cat
            val randomNumbers = (1..10).random()
            gameLevel = randomNumbers
            println("Level:")
            println(gameLevel)

            // Run levelLoad Function, setting up level!
            levelLoad(gameLevel)

            gameFunction(gameStarted, gameLevel, playerScore) // Runs the game function
            startButton.setVisibility(View.INVISIBLE)       // Remove Button
            hitboxButton.setVisibility(View.INVISIBLE)
        }
        // Starts the game with hitboxes on
        hitboxButton.setOnClickListener {
            gameStarted = 1
            hitboxOn = 1
            chronoTimer.start()
            musicBG.start()

            // Gives background to Cat Button
            catPress.backgroundTintList = getColorStateList(android.R.color.holo_purple)

            val randomNumbers = (1..10).random()
            gameLevel = randomNumbers
            println("Level:")
            println(gameLevel)
            levelLoad(gameLevel)
            gameFunction(gameStarted, gameLevel, playerScore)
            startButton.setVisibility(View.INVISIBLE)
            hitboxButton.setVisibility(View.INVISIBLE)
        }
    }
}

// PUT THIS IN XML LATER, TRANSPARENT BUTTON!
// android:background="#00FFFFFF"   - Invisible
// android:background="#22FF0000"   - Hitbox