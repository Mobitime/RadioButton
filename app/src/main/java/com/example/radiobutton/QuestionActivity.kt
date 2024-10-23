package com.example.radiobutton

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text

class QuestionActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("Какое сражение считается самым крупным в истории России?", "Бородинская битва", "Сталинградская битва", "Куликовская битва", 1),
        Question("Какой русский царь отменил крепостное право?", "Петр I", "Александр II", "Николай II", 1),
        Question("Какой русский композитор написал 'Щелкунчика'?", "Чайковский", "Рахманинов", "Глинка", 0),
        Question("Кто был первым президентом России?", "Борис Ельцин", "Владимир Путин", "Дмитрий Медведев", 0),
        Question("Когда была основана Москва?", "1147", "1237", "1380", 0)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val result = intent.getIntExtra("result", 0)
        currentQuestionIndex = intent.getIntExtra("currentIndex", 0)
        displayQuestion()

        val submitButton: Button = findViewById(R.id.submitButton)
        submitButton.setOnClickListener {
            val selectedOptionId = findViewById<RadioGroup>(R.id.optionsGroup).checkedRadioButtonId
            val points = calculatePoints(selectedOptionId)

            if (currentQuestionIndex < questions.size - 1) {
                val intent = Intent(this, QuestionActivity::class.java)
                intent.putExtra("result", result + points)
                intent.putExtra("currentIndex", currentQuestionIndex + 1)
                startActivity(intent)
            }else{
                val intent = Intent(this, Result::class.java )
                intent.putExtra("result", result + points)
                startActivity(intent)
            }
        }
    }
    private fun displayQuestion(){
        val question = questions[currentQuestionIndex]
        val questionTextView = findViewById<TextView>(R.id.questionTextView)
        questionTextView.text = question.text

        val option1 = findViewById<RadioButton>(R.id.option1)
        val option2 = findViewById<RadioButton>(R.id.option2)
        val option3 = findViewById<RadioButton>(R.id.option3)

        option1.text = question.option1
        option2.text = question.option2
        option3.text = question.option3

        findViewById<RadioGroup>(R.id.optionsGroup).clearCheck()
    }

    private fun calculatePoints(selectedOptionId: Int): Int{
        return when ( selectedOptionId){
            R.id.option1 -> if (questions[currentQuestionIndex].correctAnswerIndex == 0) 100 else 0
            R.id.option2 -> if (questions[currentQuestionIndex].correctAnswerIndex == 1) 100 else 0
            R.id.option3 -> if (questions[currentQuestionIndex].correctAnswerIndex == 2) 100 else 0
            else -> 0
        }
    }
    data class Question(val text: String, val option1: String, val option2: String, val option3: String, val correctAnswerIndex: Int)
}

