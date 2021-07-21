package com.cloud.matematikhesabioyunu

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    private var userName : String? = null
    private var userScore : Int = 0
    private var bestPlayerScore : Int = 0
    lateinit var sharedPreferences : SharedPreferences
    private var result : String = ""
    private var best : String = "Malesef en yüksek puanı geçemedin."


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        sharedPreferences = this.getSharedPreferences("com.cloud.matematikhesabioyunu",
            Context.MODE_PRIVATE)

        val player = SingletonClass.Player
        val intent = intent
        userName = player.playerName
        userScore = intent.getIntExtra("kullaniciPuani",0)

        bestPlayerScore = player.playerScore ?:0






        if (userScore > bestPlayerScore ){
            result = "S"
            best = "Tebrikler en yüksek puanı geçtin."
            sharedPreferences.edit().putString("kullaniciAdi",userName).apply()
            sharedPreferences.edit().putInt("kullaniciPuani",userScore).apply()
        }
        else if(userScore>30){
            result = "A"
        }
        else if(userScore>20){
            result = "B"
        }
        else if(userScore>10){
            result = "C"
        }
        else if(userScore>5){
            result = "D"
        }
        else if(userScore>0){
            result = "E"
        }
        else{
            result = "F"
        }

        textViewScoreLetter.text = result
        textViewScore.text = "Puanın: ${userScore}"
        textViewBestScore.text = best

    }
}