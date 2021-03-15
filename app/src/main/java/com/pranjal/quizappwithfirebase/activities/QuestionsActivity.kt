package com.pranjal.quizappwithfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.pranjal.quizappwithfirebase.R
import com.pranjal.quizappwithfirebase.adapters.OptionAdapter
import com.pranjal.quizappwithfirebase.models.Question
import com.pranjal.quizappwithfirebase.models.Quiz

class QuestionsActivity : AppCompatActivity() {

    val previous= findViewById<Button>(R.id.btnPrevious)
    val submit= findViewById<Button>(R.id.btnSubmit)
    val next= findViewById<Button>(R.id.btnNext)


    var quizzes : MutableList<Quiz>? = null
    var questions: MutableMap<String, Question>? = null
    var index = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        setUpFirestore()
        setUpEventListener()
    }

    private fun setUpEventListener() {
        previous.setOnClickListener {
            index--
            bindViews()
        }

        next.setOnClickListener {
            index++
            bindViews()
        }

        submit.setOnClickListener {
            Log.d("FINALQUIZ", questions.toString())

            //serialization
            val intent = Intent(this, ResultActivity::class.java)
            val json  = Gson().toJson(quizzes!![0])
            intent.putExtra("QUIZ", json)
            startActivity(intent)
        }
    }

    private fun setUpFirestore() {
        val firestore = FirebaseFirestore.getInstance()
        var date :String? = intent.getStringExtra("DATE")

        if (date != null) {
            firestore.collection("quizzes").whereEqualTo("title", date)
                    .get()

                    .addOnSuccessListener {
                        if(it != null && !it.isEmpty){
                            quizzes = it.toObjects(Quiz::class.java)
                            questions = quizzes!![0].questions
                            bindViews()
                        }
                    }
        }
    }

    private fun bindViews() {
        previous.visibility = View.GONE
        submit.visibility = View.GONE
        next.visibility = View.GONE

        if(index == 1){ //first question
            next.visibility = View.VISIBLE
        }
        else if(index == questions!!.size) { // last question
            submit.visibility = View.VISIBLE
            previous.visibility = View.VISIBLE
        }
        else{ // Middle
            previous.visibility = View.VISIBLE
            next.visibility = View.VISIBLE
        }

        val question = questions!!["question$index"]

        question?.let {
            val descriptionText =findViewById<TextView>(R.id.description)
            descriptionText.text = it.description

            val optionAdapter = OptionAdapter(this, it)

            val optionListRecyclerView =findViewById<RecyclerView>(R.id.optionList)
            optionListRecyclerView.layoutManager = LinearLayoutManager(this)
            optionListRecyclerView.adapter = optionAdapter
            optionListRecyclerView.setHasFixedSize(true)
        }
    }
}