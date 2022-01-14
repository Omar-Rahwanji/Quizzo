package com.example.quizzzo

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class ChooseQuiz : AppCompatActivity() {

    //private lateinit var btnSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_quiz)

        var btnArr = arrayListOf<Button>()

        initView()
        /*
        btnLogin.setOnClickListener { view ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
                btnSignup.setOnClickListener { addUser() }

        */

        val constraintLayout = findViewById<ConstraintLayout>(R.id.constraintLayout)

        val db = baseContext.openOrCreateDatabase("quizzzo.db", Context.MODE_PRIVATE,null)
        val sqlStatement = "SELECT * FROM Categories;"

        var ourContext = this

        val query = db.rawQuery(sqlStatement,null)
        query.use {
            if(it.moveToFirst())
            {
                with(it)
                {
                    val id = getInt(0)
                    val desc = getString(1)

                    btnArr.add(Button(ourContext))
                    val button = btnArr[btnArr.size-1]
                    button.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
                    button.text = desc
                    button.id = id
                    button.setOnClickListener(View.OnClickListener {
                        button.text = "You just clicked me";
                        goToQuiz(button);
                    })
                    button.setBackgroundColor(Color.GREEN)
                    button.setTextColor(Color.RED)
                    constraintLayout.addView(button)
                }
            }
        }
    }

    private fun goToQuiz(button : Button)
    {
        val intent = Intent(this, Quiz::class.java);
        intent.putExtra("categoryId",button.id.toString())

        startActivity(intent);
        finish();
    }

    private fun initView() {
        /*
        edFName = findViewById(R.id.edFName)
        edLName = findViewById(R.id.edLName)
        edEmail = findViewById(R.id.edEmail)
        edPassword = findViewById(R.id.edPassword)
        edCPassword = findViewById(R.id.edCPassword)
        btnSignup = findViewById(R.id.btnSignup)
        btnLogin = findViewById(R.id.btnLogin)
        */

    }

}