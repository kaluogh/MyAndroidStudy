package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import java.lang.StringBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var beforeButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionView: TextView
    private var questionIndex = 0
    private var questionBank = listOf<Question>(
        Question(R.string.question_africa, false),
        Question(R.string.question_asiz, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, true),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        questionView = findViewById(R.id.quest_view)
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        beforeButton = findViewById(R.id.before_button)
        nextButton = findViewById(R.id.next_button)


        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        beforeButton.setOnClickListener { view: View ->
            if (questionIndex > 0) {
                questionIndex --
            }
            updateQuesttion(questionIndex)
        }

        nextButton.setOnClickListener { view: View ->
            if (questionIndex < questionBank.size - 1) {
                questionIndex ++
            }
            updateQuesttion(questionIndex)
        }

        updateQuesttion(0)
    }

    private fun checkAnswer(answer: Boolean) {
        var flag = answer == questionBank[questionIndex].answer
        if (flag) {
            showMessage(R.string.correct_toast)
        } else {
            showMessage(R.string.incorrect_toast)
        }
    }

    private fun showMessage(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuesttion(index: Int) {
        @StringRes var resId = questionBank[index].textResId
//        questionView.setText(resId)
        questionView.setText((index + 1).toString() + ": " + getString(resId))
    }
}