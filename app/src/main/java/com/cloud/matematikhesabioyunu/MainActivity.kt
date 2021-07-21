package com.cloud.matematikhesabioyunu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var userScore : Int = 0
    private var conclusion : Int = 0
    private var number1 : Int  = 0
    private var number2 : Int = 0
    private var operation : Int = 0
    private var dividend : Int = 0
    private var answer : Int? = null
    private var  mMyToast : Toast? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextAnswer.requestFocus()

        game()


        object : CountDownTimer(15000,1000){
                override fun onTick(millisUntilFinished: Long) {
                    textTime.text = "Süre: ${millisUntilFinished/1000}"
                }
                override fun onFinish() {
                    Toast.makeText(applicationContext,"Süre Bitti",Toast.LENGTH_LONG).show()
                    val intent = Intent(applicationContext,ResultActivity::class.java)
                    intent.putExtra("kullaniciPuani",userScore)
                    startActivity(intent)
                    finish()

                }
            }.start()

    }

    fun game(){
        number1 = (1..12).random()
        number2 = (1..10).random()
        operation = (0..3).random()
        if(userScore > 11){
            number1 += 3
            number2 += 3
        }
        if (number2 > number1){
            var temp = number1
            number1 = number2
            number2 = temp
        }
        when(operation){
            0-> {textQuestion.text = "${number1}+${number2}"
            conclusion = number1 + number2}
            1-> {textQuestion.text = "${number1}-${number2}"
                conclusion = number1 - number2}
            2-> {textQuestion.text = "${number1}*${number2}"
            conclusion = number1 * number2}
            3-> { dividend = number1 * number2
                textQuestion.text = "${dividend}/${number2}"
            conclusion = number1}
        }
    }

     fun check(view : View){
         answer = editTextAnswer.text.toString().toIntOrNull()
         if (answer == null){
             toastService("Cevap Girin!")
         }
         else{
             if (conclusion == answer){
                 userScore += 1
                 toastService("DOĞRU!")
             }
             else{
                 userScore -= 1
                 toastService("YANLIŞ!")
             }
             textScore.text = "Puan: ${userScore}"
             editTextAnswer.text.clear()
             game()
         }


     }

    fun toastService(text:String){
        if(mMyToast!=null){
            mMyToast!!.cancel()
        }
        mMyToast = Toast.makeText(this,text,Toast.LENGTH_SHORT);
        mMyToast!!.show();
    }
}