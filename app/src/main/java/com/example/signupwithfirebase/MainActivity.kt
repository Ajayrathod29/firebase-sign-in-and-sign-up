package com.example.signupwithfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signUpButton = findViewById<Button>(R.id.btn)
        val etName = findViewById<TextInputEditText>(R.id.etname)
        val eMail = findViewById<TextInputEditText>(R.id.etmail)
        val ePass = findViewById<TextInputEditText>(R.id.etpass)
        val userId = findViewById<TextInputEditText>(R.id.etuniq)

        signUpButton.setOnClickListener {
            val name =etName.text.toString()
            val email =eMail.text.toString()
            val Password = ePass.text.toString()
            val uniqueId= userId.text.toString()

            val user = User(name,email,Password,uniqueId)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(uniqueId).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                eMail.text?.clear()
                ePass.text?.clear()
                userId.text?.clear()

                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnSuccessListener {
                Toast.makeText( this,"Failed", Toast.LENGTH_SHORT).show()
            }
        }
        val signIntext = findViewById<TextView>(R.id.tvSignIn)
        signIntext.setOnClickListener{
            val openLogin = Intent(this,LogIn::class.java)
            startActivity(openLogin)

        }
    }

}