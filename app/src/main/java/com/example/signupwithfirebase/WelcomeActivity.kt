package com.example.signupwithfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        val name = intent.getStringExtra(LogIn.KEY2)
        val mail= intent.getStringExtra(LogIn.KEY1)
        val userId = intent.getStringExtra(LogIn.KEY3)

        val welcomeText= findViewById<TextView>(R.id.tvWelcome)
        val mailText= findViewById<TextView>(R.id.tvMail)
        val idText= findViewById<TextView>(R.id.tvUnique)
        welcomeText.text= "Welcome $name"
        mailText.text="Mail : $mail"
        idText.text="UserId: $userId"
    }
}