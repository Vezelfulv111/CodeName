package com.game.codename

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.MotionEvent
import androidx.appcompat.widget.Toolbar
import com.game.codename.Settings.Companion.Back_size
import java.io.*

class Set_game : AppCompatActivity() {
    private lateinit var Captain: Button
    private lateinit var Player: Button
    private lateinit var Info: TextView
    private lateinit var Whattodo: TextView
    private lateinit var EnterNum: EditText
    private lateinit var Nextscreen: Button
    private lateinit var GEN: Button
    private lateinit var Toolbar: Toolbar

    var FileLan = "language.txt"
    var FileLanguage = File("/data/data/com.Sp.code.game.codename/files/" + File.separator + FileLan)
    var Language = 0

    var OUR_KEY = 10

    private var mediaPlayer: MediaPlayer? = null

    var Field_SIZE_1 =25;

    companion object{
        const val Field_SIZE ="Field_SIZE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        //взяли значение с настроек
        Field_SIZE_1 = this.intent.getIntExtra(Field_SIZE, 25)



        Toolbar = findViewById(R.id.atoolbar)
        setSupportActionBar(Toolbar)


        Captain= findViewById(R.id.Captain)
        Player= findViewById(R.id.Player)
       // Player.setBackgroundResource(R.drawable.gradient_toolbar);
        //Player.backgroundTintList=BackgroundResource(R.drawable.gradient_1)

        Info= findViewById(R.id.info)
        Whattodo= findViewById(R.id.whattodo)
        EnterNum= findViewById(R.id.Number)

        Nextscreen= findViewById(R.id.nextscreen)
        Nextscreen.visibility = View.INVISIBLE

        GEN= findViewById(R.id.GEN)
        GEN.visibility = View.GONE

        EnterNum.visibility = View.INVISIBLE
        Info.visibility = View.INVISIBLE
        Whattodo.visibility = View.INVISIBLE

        //проверка языка
        if (FileLanguage.exists()) {
            try {
                // открываем поток для чтения
                val br = BufferedReader(InputStreamReader(openFileInput(FileLan)))
                var str: String? = ""
                while (br.readLine().also { str = it } != null) {
                    Language = Integer.valueOf(str)
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        if (Language==1)
        { Captain.text="Captain team (playing from 2 phones)"
          Player.text="Player team"
            EnterNum.hint ="Enter"
            Nextscreen.text ="Continue"
            GEN.text = "Generate"

        }




        Toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.GoBACK ->
                {

                val Intent1 = Intent(this, Settings::class.java)
                    Intent1.putExtra(Back_size,Field_SIZE_1)
                startActivity(Intent1)//зашли на экран настроек
                   //звук перехода
                    var mediaPlayer= MediaPlayer.create(this,R.raw.grab_cards)
                    mediaPlayer?.start()
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer?.release()
                        mediaPlayer = null  }
                }
            }
            true
        }

    }

    //добавляем к тулбару элементы
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.set_menu,menu)
      return true
    }




    var flag = 1;

    private var WhichIntent = 1;

    fun ChoiceTeam (view: View) {
        val animE_1 = AnimationUtils.loadAnimation(this, R.anim.first1buttn)
        val animE_2 = AnimationUtils.loadAnimation(this, R.anim.first2buttn)

        val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.frominvise)

        if (Captain.getVisibility() == View.VISIBLE) //проверка чтобы звук не срабатывал
        {   var mediaPlayer = MediaPlayer.create(this, R.raw.first_sound)
            mediaPlayer?.start()
            //нужно анулировать медиа плеер чтобы было все ок и звук не пропадал
            mediaPlayer.setOnCompletionListener {
                mediaPlayer?.release()
                mediaPlayer = null // finish current activity
            }
        }



        if (flag==1) {
            when (view.id) {
                R.id.Captain -> {
                    //Поменяли переход - переходим на экран капитана
                    WhichIntent=0
                    Captain.visibility = View.INVISIBLE
                    Player.visibility = View.INVISIBLE
                        Captain.startAnimation(animE_1);
                        Player.startAnimation(animE_2);

                    flag = 0;
                        Whattodo.startAnimation(animE_Invis)
                        EnterNum.startAnimation(animE_Invis)
                    Whattodo.visibility = View.VISIBLE
                    EnterNum.visibility = View.VISIBLE
                    Whattodo.text = "Введите номер поля с телефона игроков"
                         Nextscreen.startAnimation(animE_Invis)
                    Nextscreen.visibility = View.VISIBLE
                    Nextscreen.text = "Ввести"

                    if (Language==1)
                    {   Whattodo.text="Enter field number from players phone"
                        Nextscreen.text="Enter"
                    }
                }

                R.id.Player -> {
                    Captain.startAnimation(animE_1);
                    Player.startAnimation(animE_2);
                    flag = 0;
                    Captain.visibility = View.INVISIBLE
                    Player.visibility = View.INVISIBLE
                        Whattodo.startAnimation(animE_Invis)
                        Info.startAnimation(animE_Invis)
                        GEN.startAnimation(animE_Invis)
                    Whattodo.visibility = View.VISIBLE
                    Whattodo.text = "Сгенерируете номер"
                    Info.visibility = View.VISIBLE
                    Info.text = "Номер"
                    GEN.visibility = View.VISIBLE
                    if (Language==1)
                    {   Whattodo.text="Generated number"
                        Info.text="Number"
                    }
                }
            }
        }
    }



    fun Nextscreen (view: View)
    {
        var mediaPlayer= MediaPlayer.create(this,R.raw.first_sound)
        mediaPlayer?.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null // finish current activity
        }

        Nextscreen.text = "Продолжить"
        if (Language==1)
        { Nextscreen.text ="Continue"
        }
            if (WhichIntent==1) {

                val WhatWeGot = Info.text.toString()
                val WeGot = Integer.parseInt(WhatWeGot)

                if (Field_SIZE_1==25)
                {val Intent_player = Intent(this, MainActivity::class.java)
                Intent_player.putExtra(MainActivity.KEY,WeGot)
                startActivity(Intent_player)}
                else
                {val Intent_player = Intent(this, MainActivity_2::class.java)
                    Intent_player.putExtra(MainActivity_2.KEY,WeGot)
                    startActivity(Intent_player)}

            }
            if (WhichIntent==0) //капитанский экран идем на
            {
                val WhatWeGot = EnterNum.text.toString()
                val length = WhatWeGot.length//проверяем что в строке 4 символа

                if (length ==4)
                {val WeGot = Integer.parseInt(WhatWeGot)
                    if (Field_SIZE_1==25)
                    { val Intent_cap = Intent(this, CaptainScreen::class.java)
                        Intent_cap.putExtra(CaptainScreen.KEY,WeGot)
                        startActivity(Intent_cap)}
                    else
                    {val Intent_cap = Intent(this, Captain_65::class.java)
                        Intent_cap.putExtra(Captain_65.KEY,WeGot)
                        startActivity(Intent_cap)}
                }
                else
                {Whattodo.text = "Код - 4 символа"
                    if (Language==1)
                    { Whattodo.text ="Code - 4 symbols"
                    }}
            }
    }

    var f1_gen = 1
    fun GEN (view: View)
    {

        var mediaPlayer= MediaPlayer.create(this,R.raw.first_sound)
        mediaPlayer?.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null // finish current activity
        }

        Info.text = OUR_KEY.toString()

        Nextscreen.text = "Продолжить"
        if (Language==1)
        { Nextscreen.text ="Continue"
        }

        if (f1_gen==1)
        {val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.frominvise)
        Nextscreen.startAnimation(animE_Invis)}
        f1_gen=0;
        Nextscreen.visibility = View.VISIBLE

        val fstValue = (1..9).random()//инвертируем или нет массив

        val second = (1..9).random()
        val third = (1..9).random()//эти 2 цифры - это номера полей

        val end = (0..9).random()//это означет унас красных или синих карточек больше - след экран четные или нечетные

        val itog = fstValue*1000+second*100+third*10+end


        OUR_KEY = itog;
        Info.text =  OUR_KEY.toString()
    }


    private fun handleToch(event:MotionEvent)
    {
        when (event.action)
        {MotionEvent.ACTION_DOWN -> {
            mediaPlayer?.start() }
        }
    }



    }




