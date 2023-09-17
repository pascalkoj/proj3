package com.example.project3

enum class DifficultyLevel
{
    EASY, MEDIUM, HARD
}
enum class OperationMode
{
    ADDITION, MULTIPLICATION, DIVISION, SUBTRACTION
}

class MathApp {

    var difficultyLevel = DifficultyLevel.EASY
    var numQuestions = 10



    companion object {
        val instance:MathApp by lazy {
            MathApp()
        }
    }
}