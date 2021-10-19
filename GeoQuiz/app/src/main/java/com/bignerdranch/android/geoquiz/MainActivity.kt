package com.bignerdranch.android.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.lang.StringBuilder

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var beforeButton: Button
    private lateinit var nextButton: Button
    private lateinit var beforeImageButton: ImageButton
    private lateinit var nextImageButton: ImageButton
    private lateinit var questionView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_main)

//        var quizViewModel = ViewModelProviders.of(this).get(QuizViewModel::class.java)
//        Log.d(TAG, "Get quizViewModel.")

        questionView = findViewById(R.id.quest_view)
        falseButton = findViewById(R.id.false_button)
        trueButton = findViewById(R.id.true_button)
        beforeButton = findViewById(R.id.before_button)
        nextButton = findViewById(R.id.next_button)
        beforeImageButton = findViewById(R.id.before_imagebutton)
        nextImageButton = findViewById(R.id.next_imagebutton)


        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        beforeButton.setOnClickListener { view: View ->
            quizViewModel.moveBefore()
            updateQuesttion()
        }

        beforeImageButton.setOnClickListener { view: View ->
            quizViewModel.moveBefore()
            updateQuesttion()
        }

        nextButton.setOnClickListener { view: View ->
            quizViewModel.moveNext()
            updateQuesttion()
        }

        nextImageButton.setOnClickListener { view: View ->
            quizViewModel.moveNext()
            updateQuesttion()
        }

        updateQuesttion()
    }

    private fun checkAnswer(answer: Boolean) {
        var flag = answer == quizViewModel.currentQuestionAnswer
        if (flag) {
            showMessage(R.string.correct_toast)
        } else {
            showMessage(R.string.incorrect_toast)
        }
    }

    private fun showMessage(@StringRes message: Int) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun updateQuesttion() {
        var index: Int = quizViewModel.questionIndex
        @StringRes var resId = quizViewModel.currentQuestionText
//        questionView.setText(resId)
        questionView.setText((index + 1).toString() + ": " + getString(resId))
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }
}