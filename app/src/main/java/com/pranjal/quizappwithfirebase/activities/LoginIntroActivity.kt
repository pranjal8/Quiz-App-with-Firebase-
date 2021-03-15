package com.pranjal.quizappwithfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.pranjal.quizappwithfirebase.R

class LoginIntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_intro)

        //firebase object
        val auth = FirebaseAuth.getInstance()

        //if already logged in then goto to main activity
        if(auth.currentUser != null){
            Toast.makeText(this, "Already logged in", Toast.LENGTH_SHORT).show()
            redirect("MAIN")
        }

        val startButton: Button =findViewById(R.id.btnGetStarted)
        startButton.setOnClickListener {

             redirect("LOGIN")
        }
    }

    private fun redirect(name:String){

        val intent =when(name){

            "LOGIN" -> Intent(this, LoginActivity::class.java)

            "MAIN" -> Intent(this, MainActivity::class.java)

            else-> throw Exception("No path exists")
        }
        startActivity(intent)
        finish()
    }
}