package com.example.signupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.Key

class LogIn : AppCompatActivity() {
    lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY1="com.example.signupwithfirebase.LogIn.email"
        const val KEY2="com.example.signupwithfirebase.LogIn.name"
        const val KEY3="com.example.signupwithfirebase.LogIn.userId"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        val signInButton = findViewById<Button>(R.id.btnsignIn)
        val userName =findViewById<TextInputEditText>(R.id.userNameEditText)

        signInButton.setOnClickListener {
            val uniqueId = userName.text.toString()
            if(uniqueId.isNotEmpty()){
                readData(uniqueId)
            }else{
                Toast.makeText(this, "please enter user name", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(uniqueId: String){
        databaseReference= FirebaseDatabase.getInstance().getReference("Users")


        databaseReference.child(uniqueId).get().addOnSuccessListener {

            // if user exit or not
            if(it.exists()) {
                // welcome user in your app,with intent and also pass
              val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value
                val intentWelcome = Intent(this,WelcomeActivity::class.java)
                intentWelcome.putExtra(KEY1,email.toString())
                intentWelcome.putExtra(KEY2,name.toString())
                intentWelcome.putExtra(KEY3,userId.toString())
                startActivity(intentWelcome)
            } else {
                Toast.makeText(this, "User does not exists", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener{
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
    }
}