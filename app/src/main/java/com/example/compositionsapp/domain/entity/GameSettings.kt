package com.example.compositionapp.domain.entity

data  class GameSettings(
    val maxSumValue :Int,
    val minCountOfRightAnswers:Int,
    val minPercentOfRightAnswers:Int,
    val gameTimeSeconds:Int
)