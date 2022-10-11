package com.example.quizapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityResultsBinding

class ResultsActivity : AppCompatActivity() {
private lateinit var binding:ActivityResultsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val userName = intent.getStringExtra("USER_KEY")
        val maxQuestions = intent.getStringExtra("MAX_KEY")!!.toInt()
        val wrongAnswers = intent.getStringExtra("WRONG_KEY")!!.toInt()

        binding.txtUsername.text = userName
        binding.txtScore.text = "Your score: ${maxQuestions - wrongAnswers}" + "/" + "${maxQuestions}"
        binding.btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}