package com.example.quizapp

class Questionnaire {

    fun getQuestions():MutableList<Question>{
        val questionList:MutableList<Question> = mutableListOf()

        val que1 = Question(1,"What country does this flag belong to?", R.drawable.flag_albania,
        "Armenia","Antigua and Barbuda",
            "Bahrain", "Albania", 4)
        questionList.add(que1)
        val que2 = Question(2,"What country does this flag belong to?", R.drawable.flag_france,
            "Guyana","France",
            "Georgia", "Finland", 2)
        questionList.add(que2)
        val que3 = Question(3,"What country does this flag belong to?", R.drawable.flag_india,
            "Dominica","India",
            "Djibouti", "Germany", 2)
        questionList.add(que3)
        val que4 = Question(4,"What country does this flag belong to?", R.drawable.flag_mexico,
            "Mexico","Cyprus",
            "Chad", "Congo-Brazzaville", 1)
        questionList.add(que4)
        val que5 = Question(5,"What country does this flag belong to?", R.drawable.flag_nigeria,
            "Burkina Faso","South Africa",
            "Nigeria", "Cape Verde", 3)
        questionList.add(que5)
        val que6 = Question(6,"What country does this flag belong to?", R.drawable.flag_zambia,
            "Sudan","Zambia",
            "Gabon", "Senegal", 2)
        questionList.add(que6)

        questionList.shuffle()
    return questionList
    }
}