package com.pranjal.quizappwithfirebase.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.google.gson.Gson
import com.pranjal.quizappwithfirebase.R
import com.pranjal.quizappwithfirebase.models.Quiz

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    val textAns =findViewById<TextView>(R.id.txtAnswer)
    val textScore =findViewById<TextView>(R.id.txtScore)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setUpViews()
    }

    private fun setUpViews() {
        //deserialized
        val quizData = intent.getStringExtra("QUIZ")
        quiz = Gson().fromJson<Quiz>(quizData, Quiz::class.java)

        calculateScore()
        setAnswerView()
    }
    private fun setAnswerView() {
        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textAns.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            textAns.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries) {
            val question = entry.value
            if (question.answer == question.userAnswer) {
                score += 10
            }
        }
        textScore.text = "Your Score : $score"
    }

}