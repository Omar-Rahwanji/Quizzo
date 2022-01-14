package com.example.quizzzo

import android.content.ContentValues
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.quizzzo.database.Query

class AdminDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)
        val spinner = findViewById<Spinner>(R.id.spinner)
        var categories = arrayOf("Math","English Grammar","Java","Machine Learning")
        val contentValues = ContentValues()

        for(category in categories){
            contentValues.put("Category_desc", category)
            Query(this).insert("Category", contentValues)
        }

        var chosenItem = -1
        var counter = 1
        spinner.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,categories)
        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                chosenItem = position+1
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        val nextQuestionButton = findViewById<Button>(R.id.btnNextQuestion)
        val doneButton = findViewById<Button>(R.id.btnDone)

        val questionText = findViewById<EditText>(R.id.tfQuestion)
        val ans1 = findViewById<EditText>(R.id.tfAns1)
        val ans2 = findViewById<EditText>(R.id.tfAns2)
        val ans3 = findViewById<EditText>(R.id.tfAns3)
        val ans4 = findViewById<EditText>(R.id.tfAns4)

        nextQuestionButton.setOnClickListener {
            if (questionText.text.isEmpty() || ans1.text.isEmpty() || ans2.text.isEmpty() || ans3.text.isEmpty() || ans4.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Required Field", Toast.LENGTH_SHORT).show()
            }
            else {
                val contentValues = ContentValues()
                contentValues.put("Category_id", chosenItem)
                contentValues.put("Question_desc", questionText.text.toString())
                contentValues.put("ans1", ans1.text.toString())
                contentValues.put("ans2", ans2.text.toString())
                contentValues.put("ans3", ans3.text.toString())
                contentValues.put("ans4", ans4.text.toString())

                val status = Query(this).insert("Questions", contentValues)
                if (status > -1) {
                    questionText.hint="Question " + (++counter)
                    questionText.text.clear()
                    ans1.text.clear()
                    ans2.text.clear()
                    ans3.text.clear()
                    ans4.text.clear()
                    Toast.makeText(this, "Question added Successfully :)", Toast.LENGTH_SHORT).show()
                }
                else {
                    Toast.makeText(this, "Failed adding the question :(", Toast.LENGTH_SHORT).show()
                }
            }
        }

        doneButton.setOnClickListener {
            onBackPressed()
        }
    }
}
