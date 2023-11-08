package com.example.compositionsapp.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.compositionsapp.R

@BindingAdapter("requiredAnswers")
fun bindRequiredAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_number_of_correct), count
    )
}

@BindingAdapter("scoreAnswer")
fun bindScoreAnswer(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.your_score), count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage_of_correct_answers), count
    )
}

@BindingAdapter("scorePercentage", "countOfQuestions")
fun bindScorePercentage(textView: TextView, countOfRightAnswers: Int, countOfQuestions: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.percentage_of_correct_answers),
        getPercentOfRightAnswer(countOfRightAnswers, countOfQuestions)
    )
}
private fun getPercentOfRightAnswer(countOfRightAnswers: Int, countOfQuestions: Int): Int =
    if (countOfQuestions == 0) 0
    else ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()


//второй вариант решения где в xml будет передан только 1 обьект
//@BindingAdapter("scorePercentage")
//fun bindScorePercentage(textView: TextView, gameResult: GameResult) {
//    textView.text = String.format(
//        textView.context.getString(R.string.score_percentage),
//        getPercentOfRightAnswers(gameResult)
//    )
//}
//
//private fun getPercentOfRightAnswers(gameResult: GameResult) = with(gameResult) {
//    if (countOfQuestions == 0) {
//        0
//    } else {
//        ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
//    }
//}
@BindingAdapter("emojiResult")
fun bindEmoji (setImageResource:ImageView,gameResult:Boolean){
    setImageResource.setImageResource(getSmileResId(gameResult))
}
private fun getSmileResId(gameResult:Boolean):Int{
    return if (gameResult) {
        R.drawable.ic_smile
    } else {
        R.drawable.ic_sad
    }
}
