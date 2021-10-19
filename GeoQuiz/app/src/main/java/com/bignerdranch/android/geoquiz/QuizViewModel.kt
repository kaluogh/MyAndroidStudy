package com.bignerdranch.android.geoquiz
import android.util.Log
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
    init {
        Log.d(TAG, "QuizViewModel instance init.")
    }

    var questionIndex = 0
    private var questionBank: List<Question> = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_asiz, true),
        Question(R.string.question_australia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, true),
    )

    val currentQuestion: Question get() = questionBank[questionIndex]
    val currentQuestionAnswer: Boolean get() = currentQuestion.answer
    val currentQuestionText: Int get() = currentQuestion.textResId

    fun moveBefore() {
        if (questionIndex > 0) {
            questionIndex --
        }
    }

    fun moveNext() {
        if (questionIndex < questionBank.size - 1) {
            questionIndex ++
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "QuizViewModel instance onCleared.")
    }
}