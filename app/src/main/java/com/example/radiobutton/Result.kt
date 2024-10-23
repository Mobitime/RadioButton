package com.example.radiobutton

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)

        val result = intent.getIntExtra("result", 0)
        val resultTV: TextView = findViewById(R.id.resultTV)
        val descriptionTV: TextView = findViewById(R.id.descriptionTV)

        resultTV.text = "Ваши баллы: $result"


        val description = when ( result) {
            in 0..199 -> "Плохие знания"
            in 200..299 -> "Удовлетворительные знания"
            in 300..399 -> "Хорошие знания"
            in 400..499 -> "Отличные знания"
            500 -> "Да вы ботаник"
            else -> "Ошибка"
        }
        descriptionTV.text = description
    }
}