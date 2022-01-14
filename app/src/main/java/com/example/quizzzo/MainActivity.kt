package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzzo.database.Model
import com.example.quizzzo.database.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE, null)
        val m = Model(this)
        m.onCreate(db)

        val btnSignUp: Button = findViewById(R.id.btnSignup)
        btnSignUp.setOnClickListener { view ->
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        val btnLogin: Button = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { view ->
            var helper = Query(applicationContext)
            var db = helper.readableDatabase
            val edEmail: EditText = findViewById(R.id.edEmail)
            val edPassword: EditText = findViewById(R.id.edPassword)
            var args =
                listOf<String>(edEmail.text.toString(), edPassword.text.toString()).toTypedArray()
            var admin = db.rawQuery("select * from Admins where email = ? and password = ?", args)

            if (admin.moveToNext())
                Toast.makeText(applicationContext, admin.getString(2).toString(), Toast.LENGTH_LONG).show() //Temp for now
            else {
                var user = db.rawQuery("select * from Users where email = ? and password = ?", args)
                if (user.moveToNext())
                    Toast.makeText(applicationContext, admin.getString(2).toString(), Toast.LENGTH_LONG).show() //Temp for now
                else
                    Toast.makeText(applicationContext, "Not Welcome", Toast.LENGTH_LONG).show()
            }
        }
    }

}