package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuizQuestionsBinding

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityQuizQuestionsBinding
    private var mCurrentPosition:Int = 1
    private var mQuestionsList:MutableList<Question>? = null
    private var mSelectedOptionPosition:Int = 0
    private lateinit var question:Question
    private var xBool:Boolean = false
    private var newId:Int = 1001
    private var wrongAnswers:Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val questionnaire = Questionnaire()
        mQuestionsList = questionnaire.getQuestions()
        setQuestion()
        binding.btnSubmit.isClickable = true
        binding.btnSubmit.setOnClickListener(this)
    }

    private fun setQuestion(){
        question = mQuestionsList!![mCurrentPosition - 1]

        defaultOptions()

        binding.progressBar.progress = mCurrentPosition
        binding.txtProgress.text = "${mCurrentPosition}" + "/" + "${binding.progressBar.max}"
        binding.imgFlag.setImageResource(question.image)
        binding.txtOptionOne.text = question.optionOne
        binding.txtOptionTwo.text = question.optionTwo
        binding.txtOptionThree.text = question.optionThree
        binding.txtOptionFour.text = question.optionFour
        binding.btnSubmit.text = getString(R.string.submit)
    }

    private fun defaultOptions(){
        val options = textArray()

        for(txt in options){
            txt.isClickable
            txt.setOnClickListener(this)
        }

        for(option in options){
            option.setTextColor(Color.parseColor("#3C3A3A"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.quiz_option_background)
            xBool = false
        }

    }

    private fun selectedOptionsView(txtView:TextView, selectedOptionNumber:Int){
        defaultOptions()
        mSelectedOptionPosition = selectedOptionNumber

        txtView.setTextColor(Color.parseColor("#8445C3"))
        txtView.setTypeface(txtView.typeface, Typeface.BOLD)
        txtView.background = ContextCompat.getDrawable(this, R.drawable.selected_quiz_option_background)
        xBool = true
    }

    override fun onClick(v: View?) {
        try{
            val button: Button? = findViewById(newId)
            when(v?.id){
                R.id.txt_option_one -> selectedOptionsView(binding.txtOptionOne, 1)
                R.id.txt_option_two -> selectedOptionsView(binding.txtOptionTwo, 2)
                R.id.txt_option_three -> selectedOptionsView(binding.txtOptionThree, 3)
                R.id.txt_option_four -> selectedOptionsView(binding.txtOptionFour, 4)
                R.id.btn_submit -> onButtonClicked(binding.btnSubmit)
                newId -> newQuestion(button)
            }
        }catch(e:Exception){
            when(v?.id){
                R.id.txt_option_one -> selectedOptionsView(binding.txtOptionOne, 1)
                R.id.txt_option_two -> selectedOptionsView(binding.txtOptionTwo, 2)
                R.id.txt_option_three -> selectedOptionsView(binding.txtOptionThree, 3)
                R.id.txt_option_four -> selectedOptionsView(binding.txtOptionFour, 4)
                R.id.btn_submit -> onButtonClicked(binding.btnSubmit)
            }
        }

    }

    private fun onButtonClicked(btn: Button){
            if (xBool){
                changeTextViewBackground()
                changeButtonText(btn)
                btn.id = newId
            }else{
                Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show()
            }
    }

    private fun newQuestion(btn:Button?){
        mCurrentPosition += 1
        setQuestion()
        btn?.id = R.id.btn_submit
    }

    private fun changeButtonText(btn: Button){
        if(mCurrentPosition != binding.progressBar.max){
            btn.text = getString(R.string.next_answer)
        }else{
            btn.text = getString(R.string.results)
            val userName = intent.getStringExtra("USER_KEY")
            val intent = Intent(this, ResultsActivity::class.java)
            intent.putExtra("MAX_KEY", "${binding.progressBar.max}")
            intent.putExtra("WRONG_KEY", "${wrongAnswers}")
            intent.putExtra("USER_KEY", "${userName}")
            startActivity(intent)
            finish()
        }
    }

    private fun changeTextViewBackground(){
        val options = textArray()
        val answerIndex = question.correctAnswer
        val answer = options[answerIndex - 1].text.toString()

        for(item in options){
            if(item.text == answer){
                item.background = ContextCompat.getDrawable(this,R.drawable.correct_answer_background)
                item.setTextColor(resources.getColor(R.color.dark_green))
            }
        }

        if(mSelectedOptionPosition != answerIndex){
            options[mSelectedOptionPosition - 1].background = ContextCompat.getDrawable(this,R.drawable.wrong_answer_background)
            options[mSelectedOptionPosition - 1].setTextColor(getColor(R.color.black))
            wrongAnswers += 1
        }
    }

    private fun textArray():MutableList<TextView>{
        val options:MutableList<TextView> = mutableListOf()
        options.add(0, binding.txtOptionOne)
        options.add(1, binding.txtOptionTwo)
        options.add(2, binding.txtOptionThree)
        options.add(3, binding.txtOptionFour)

        return options
    }
}
