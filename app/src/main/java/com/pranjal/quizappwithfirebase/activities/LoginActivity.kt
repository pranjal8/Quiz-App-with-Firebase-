package com.pranjal.quizappwithfirebase.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.pranjal.quizappwithfirebase.R


class LoginActivity : AppCompatActivity() {

    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        firebaseAuth = FirebaseAuth.getInstance()

        val buttonLogin : Button =findViewById(R.id.btnLogin)
        buttonLogin.setOnClickListener {
            loginUser()
        }

        //goto signup activity for registration
        val txtSignup: TextView =findViewById(R.id.txtSignup)

        txtSignup.setOnClickListener {

            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun loginUser(){
        val emailText : EditText =findViewById(R.id.loginEmailAddress)
        val passwordText : EditText =findViewById(R.id.loginPassword)

        val email : String= emailText.text.toString()
        val password :String = passwordText.text.toString()

        //validation
        if(email.isBlank() || password.isBlank() ){

            Toast.makeText(this, "Empty fields not allowed", Toast.LENGTH_SHORT).show()
            return

        }

        //login
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) {

            if(it.isSuccessful){
                Toast.makeText(this,"Successfully login", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this,"Please enter valid details", Toast.LENGTH_SHORT).show()
            }
        }
    }
}