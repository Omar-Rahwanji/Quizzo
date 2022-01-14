package com.example.quizzzo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    var tv_result: TextView = TextView(this);

    var iv_trophy: ImageView = ImageView(this);
    var tv_congratulations: TextView = TextView(this);
    var tv_name: TextView = TextView(this);
    var tv_score: TextView = TextView(this);
    var btn_finish: Button = Button(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var username = intent.getStringExtra("username")
        var score = intent.getIntExtra("score",0)
        var mxScore = intent.getIntExtra("mxScore",0)

        tv_result = findViewById<TextView>(R.id.tv_result)
        iv_trophy = findViewById<ImageView>(R.id.iv_trophy)
        tv_congratulations = findViewById<TextView>(R.id.tv_congratulations)
        tv_name = findViewById<TextView>(R.id.tv_name)
        tv_score = findViewById<TextView>(R.id.tv_score)
        btn_finish = findViewById<Button>(R.id.btn_finish)

        tv_name.text = username
        tv_score.text = "Your Score is $score out of $mxScore."

        btn_finish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, ChooseQuiz::class.java))
            finish()
        }
    }
}