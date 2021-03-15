package com.pranjal.quizappwithfirebase.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.firestore.FirebaseFirestore
import com.pranjal.quizappwithfirebase.R
import com.pranjal.quizappwithfirebase.adapters.QuizAdapter
import com.pranjal.quizappwithfirebase.models.Quiz
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    //navigate to navigation drawer after clicking on appbar icon
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    lateinit var adapter: QuizAdapter
    private var quizList = mutableListOf<Quiz>()
    lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViews()
    }

    private fun setUpViews() {
        setUpFirestore()
        setUpDrawer()
        //populateDumyData()
        setUpRecyclerView()
        setUpDatePicker()
    }

    private fun setUpDatePicker() {
        val datePicker =findViewById<FloatingActionButton>(R.id.btnDatePicker)

        datePicker.setOnClickListener {

            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DatePicker")

            datePicker.addOnPositiveButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

                val dateFormatter = SimpleDateFormat("dd-mm-yyyy")
                val date = dateFormatter.format(Date(it))

                val intent = Intent(this, QuestionsActivity::class.java)
                intent.putExtra("DATE", date)
                startActivity(intent)
            }
            datePicker.addOnNegativeButtonClickListener {
                Log.d("DATEPICKER", datePicker.headerText)

            }
            datePicker.addOnCancelListener {
                Log.d("DATEPICKER", "Date Picker Cancelled")
            }
        }
    }

    private fun setUpFirestore() {
        firestore = FirebaseFirestore.getInstance()

        val collectionReference = firestore.collection("quizzes")
        collectionReference.addSnapshotListener { value, error ->
            if(value == null || error != null){
                Toast.makeText(this, "Error while fetching data" , Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }else{
                Log.d("DATA", value.toObjects(Quiz::class.java).toString())
                quizList.clear()
                quizList.addAll(value.toObjects(Quiz::class.java))
                adapter.notifyDataSetChanged()
            }

        }
    }

    private fun populateDumyData() {
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
        quizList.add(Quiz("1","firs"))
    }

    private fun setUpRecyclerView(){
        adapter = QuizAdapter(this,quizList)

        val recyclerView =findViewById<RecyclerView>(R.id.quizRecyclerView)

        recyclerView.layoutManager =GridLayoutManager(this,2)
        recyclerView.adapter= adapter

    }

    private fun setUpDrawer() {

        val toolbar: MaterialToolbar = findViewById(R.id.appBar)
        val drawerLayout =findViewById<DrawerLayout>(R.id.mainDrawer)
        val navView = findViewById<NavigationView>(R.id.navigationView)

        setSupportActionBar(toolbar)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name)
        actionBarDrawerToggle.syncState()

        navView.setNavigationItemSelectedListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            drawerLayout.closeDrawers()
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}