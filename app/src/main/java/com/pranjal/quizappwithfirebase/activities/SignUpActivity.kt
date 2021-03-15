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


class SignUpActivity : AppCompatActivity() {

    lateinit var firebaseAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        firebaseAuth = FirebaseAuth.getInstance()

        val btnSignUp : Button = findViewById<Button>(R.id.btnSignup)
        btnSignUp.setOnClickListener {
            signUpUser()
        }
        //goto login activity
        val txtLogin: TextView =findViewById(R.id.txtLogin)

        txtLogin.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun signUpUser(){

        val emailText : EditText =findViewById(R.id.signupEmailAddress)
        val passwordText : EditText =findViewById(R.id.signupPassword)
        val confirmPasswordText :EditText =findViewById(R.id.signupConformPassword)

        val email : String= emailText.text.toString()
        val password :String = passwordText.text.toString()
        val confirmPassword :String = confirmPasswordText.text.toString()


        if(email.isBlank() || password.isBlank() || confirmPassword.isBlank()){

            Toast.makeText(this, "Complete above fields", Toast.LENGTH_SHORT).show()
            return

        }
        if(password != confirmPassword){
            Toast.makeText(this, "Please enter correct password", Toast.LENGTH_SHORT).show()
            return
        }

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){

            if(it.isSuccessful){
                Toast.makeText(this, "User login successfully", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            }else{
                Toast.makeText(this, "Error in creating user", Toast.LENGTH_SHORT).show()
            }

        }
    }

}