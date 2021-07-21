package com.cloud.matematikhesabioyunu

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import kotlin.system.exitProcess

class LoginActivity : AppCompatActivity() {
    var userName : String? = null
    lateinit var sharedPreferences : SharedPreferences
    private  var bestPlayerName : String? = null
    private var bestPlayerScore : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        sharedPreferences = this.getSharedPreferences("com.cloud.matematikhesabioyunu",
            Context.MODE_PRIVATE)

        bestPlayerName = sharedPreferences.getString("kullaniciAdi","Yok")
        bestPlayerScore = sharedPreferences.getInt("kullaniciPuani",0)

        textViewBestPlayer.text = "En yüksek puana sahip oyuncu: ${bestPlayerName} puan: ${bestPlayerScore}"



    }
    fun exit(view:View){
        moveTaskToBack(true);
        exitProcess(-1)
    }
    fun start(view: View){
        userName = editTextUserName.text.toString()
        if(userName==null || userName == "" || userName == bestPlayerName){
            Toast.makeText(applicationContext,"Lütfen farklı bir kullanıcı adı giriniz.",Toast.LENGTH_LONG).show()
        }
        else{
            println("username: ${userName}")
            val singleton = SingletonClass.Player
            singleton.playerName = userName
            singleton.playerScore = bestPlayerScore
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}