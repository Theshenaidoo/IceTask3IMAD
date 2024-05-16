package com.example.icetask3

import android.media.Image
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var rockButton: Button
    private lateinit var paperButton: Button
    private lateinit var scissorsButton: Button
    private lateinit var resultText: TextView
    private lateinit var computerChoiceImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Initialize
        rockButton = findViewById(R.id.rockButton)
        paperButton = findViewById(R.id.paperButton)
        scissorsButton = findViewById(R.id.scissorsButton)
        resultText = findViewById(R.id.resultText)
        computerChoiceImage = findViewById(R.id.computerChoiceImage)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        attachClickListeners()
    }

    private fun attachClickListeners() {
        rockButton.setOnClickListener { playGame("rock") }
        paperButton.setOnClickListener { playGame("paper") }
        scissorsButton.setOnClickListener { playGame("scissors") }
    }

    private fun playGame(userChoice: String) {
        val choices = listOf("rock","paper","scissors")
        val computerChoice = choices[Random.nextInt(choices.size)]
        val result = determineWinner(userChoice, computerChoice)
        resultText.text = result

        when (computerChoice) {
            "rock" -> computerChoiceImage.setImageResource(R.drawable.rock_image)
            "paper" -> computerChoiceImage.setImageResource(R.drawable.paper_image)
            "scissors" -> computerChoiceImage.setImageResource(R.drawable.scissors_image)
        }

    }

    private fun determineWinner(userChoice: String, computerChoice: String) : String {
        return when {
            userChoice == computerChoice -> "Draw"
            userChoice == "rock" && computerChoice == "scissors" ||
                    userChoice == "scissors" && computerChoice == "paper" ||
                    userChoice == "paper" && computerChoice == "rock" -> "You win"
            else -> "Computer wins"
        }
    }
}

