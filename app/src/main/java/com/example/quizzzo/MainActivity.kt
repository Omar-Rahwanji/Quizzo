package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzzo.database.Model

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE,null);
        val m = Model(this)
        m.onCreate(db)

        val btnSignUp: Button = findViewById(R.id.btnSignup)
        btnSignUp.setOnClickListener { view ->
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

}