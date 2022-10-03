package com.example.flashcardapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flashcardQuestion = findViewById<TextView>(R.id.flashcard_question)
        val flashcardAnswer = findViewById<TextView>(R.id.flashcard_answer)
        findViewById<TextView>(R.id.flashcard_question).setOnClickListener {
            findViewById<TextView>(R.id.flashcard_answer).visibility =TextView.VISIBLE
            findViewById<TextView>(R.id.flashcard_question).visibility =TextView.INVISIBLE
        }
        flashcardQuestion.setOnClickListener {
            flashcardAnswer.visibility = View.VISIBLE
            flashcardQuestion.visibility = View.INVISIBLE

//            Toast.makeText(this, "Question button was clicked", Toast.LENGTH_SHORT).show()
            Snackbar.make(flashcardQuestion, "Question button was clicked",
                Snackbar.LENGTH_SHORT).show()

            Log.i("hi", "Question button was clicked")
        }

        flashcardAnswer.setOnClickListener {
            flashcardAnswer.visibility = View.INVISIBLE
            flashcardQuestion.visibility = View.VISIBLE
        }
        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                result ->
        val data: Intent? = result.data

            if (data != null) {
                val questionString = data.getStringExtra("QUESTION_KEY")
                val answerString = data.getStringExtra("ANSWER_KEY")

                flashcardQuestion.text = questionString
                flashcardAnswer.text = answerString

                Log.i("hi: MainActivity", "question: $questionString")
                Log.i("hi: MainActivity", "answer: $answerString")
            } else {
                Log.i("hi: MainActivity", "Returned null data from AddCardActivity")
            }
        }

        val addQuestionButton = findViewById<ImageView>(R.id.add_question_button)
        addQuestionButton.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            resultLauncher.launch(intent)
        }


    }
}