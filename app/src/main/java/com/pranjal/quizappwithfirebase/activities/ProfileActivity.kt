package com.pranjal.quizappwithfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.pranjal.quizappwithfirebase.R

class ProfileActivity : AppCompatActivity() {

    val textEmail =findViewById<TextView>(R.id.txtEmail)
    val buttonLogout =findViewById<TextView>(R.id.btnLogout)
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        firebaseAuth = FirebaseAuth.getInstance()
        textEmail.text = firebaseAuth.currentUser?.email

        buttonLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }
    }
}