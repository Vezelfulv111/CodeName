package com.game.codename

import android.content.Intent
import android.content.res.Configuration
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.widget.Toolbar
import java.io.*
import java.util.*
import kotlin.Array as Array

class MainActivity : AppCompatActivity() {
    private lateinit var Table: TableLayout
    private lateinit var WhichTeam: TextView
    private lateinit var SHOW: Button

    private lateinit var Word01: Button
    private lateinit var Word02: Button
    private lateinit var Word03: Button
    private lateinit var Word04: Button
    private lateinit var Word05: Button

    private lateinit var Word06: Button
    private lateinit var Word07: Button
    private lateinit var Word08: Button
    private lateinit var Word09: Button
    private lateinit var Word10: Button

    private lateinit var Word11: Button
    private lateinit var Word12: Button
    private lateinit var Word13: Button
    private lateinit var Word14: Button
    private lateinit var Word15: Button

    private lateinit var Word16: Button
    private lateinit var Word17: Button
    private lateinit var Word18: Button
    private lateinit var Word19: Button
    private lateinit var Word20: Button

    private lateinit var Word21: Button
    private lateinit var Word22: Button
    private lateinit var Word23: Button
    private lateinit var Word24: Button
    private lateinit var Word25: Button

    private lateinit var Toolbar: Toolbar

    private lateinit var REDcount: TextView
    private lateinit var BLUEcount: TextView

    private lateinit var ChangeWords: Button
    private lateinit var ToMainScreen: Button

    var Gradient_array = IntArray(25)

    private val KeyStrings = arrayOf(
        "W11", "W12", "W13","W14", "W15",
        "W21","W22","W23","W24","W25",
        "W31","W32","W33", "W34","W35",
        "W41","W42","W43", "W44","W45",
        "W51","W52","W53", "W54","W55"
    )

    companion object{
        const val KEY ="KEY"
    }

    var Clicked = IntArray(25)
    var ZERO = 0;

    private var AllowShuffle = 1
    private var Lock_onClick = 0
    private var Lock_onClick_plus = 0

    var FileLan = "language.txt"
    var FileLanguage = File("/data/data/com.Sp.code.game.codename/files/" + File.separator + FileLan)
    var Language = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        setContentView(R.layout.activity_main)
        ToMainScreen= findViewById(R.id.ToMainScreen)
        //тулбар
        Toolbar = findViewById(R.id.atoolbar)
        setSupportActionBar(Toolbar)
        //2 счетчика
        REDcount=findViewById(R.id.Text_red)
        BLUEcount=findViewById(R.id.Text_blue)
        ChangeWords=findViewById(R.id.ChangeWords)
        //находим переменные
            Table = findViewById(R.id.Table)
            SHOW = findViewById(R.id.SHOW)
            WhichTeam = findViewById(R.id.WhichTeamFirst)
            Table.visibility = View.INVISIBLE

        Word01= findViewById(R.id.Word01);Word02= findViewById(R.id.Word02)
        Word03= findViewById(R.id.Word03);Word04= findViewById(R.id.Word04)
        Word05= findViewById(R.id.Word05)

        Word06= findViewById(R.id.Word06);Word07= findViewById(R.id.Word07)
        Word08= findViewById(R.id.Word08);Word09= findViewById(R.id.Word09)
        Word10= findViewById(R.id.Word10)

        Word11= findViewById(R.id.Word11); Word12= findViewById(R.id.Word12)
        Word13= findViewById(R.id.Word13);Word14= findViewById(R.id.Word14)
        Word15= findViewById(R.id.Word15)

        Word16= findViewById(R.id.Word16);Word17= findViewById(R.id.Word17)
        Word18= findViewById(R.id.Word18);Word19= findViewById(R.id.Word19)
        Word20= findViewById(R.id.Word20)

        Word21= findViewById(R.id.Word21);Word22= findViewById(R.id.Word22)
        Word23= findViewById(R.id.Word23);Word24= findViewById(R.id.Word24)
        Word25= findViewById(R.id.Word25)

        val Allbuttons = arrayOf(Word01,Word02, Word03, Word04, Word05,Word06, Word07, Word08,Word09,Word10,Word11,Word12,
            Word13,Word14,Word15,Word16,Word17,Word18,Word19,Word20,Word21,Word22,Word23,Word24,Word25)


        //раскладываем входное число на значения
        val KEY_1 = this.intent.getIntExtra(KEY, 0)

        val Valkey_1 = (KEY_1%10)%2; // последняя цифра показывает инверсию
        val Valkey_23 = (KEY_1%1000-Valkey_1)%20; //цифры  23 это номер массива
        val Valkey_4 = KEY_1/1000; //цифра 4 - это номер поворота

        val DopKey = (Valkey_1+Valkey_23+Valkey_4)%3
        var RedBlue =1;





        if(Valkey_1==0)
        {
            WhichTeam.text = "  Синие ходят первыми"
            if (Language==1)
            { WhichTeam.text = " Blue team is first"
            }

            RedBlue=-1
            REDcount.text = "8"
            BLUEcount.text="9"
        } //меняем цвет по остатку - последняя цифра

        //выбираем массив по 2 и 3й цифре
        var Word_fr_file: Array<String> =emptyArray()

        val Resourses = arrayOf(
            resources.getStringArray (R.array.F1),  resources.getStringArray (R.array.F2),
            resources.getStringArray (R.array.F3),  resources.getStringArray (R.array.F4),
            resources.getStringArray (R.array.F5),  resources.getStringArray (R.array.F6),
            resources.getStringArray (R.array.F7),  resources.getStringArray (R.array.F8),
            resources.getStringArray (R.array.F9),  resources.getStringArray (R.array.F10),
            resources.getStringArray (R.array.F11), resources.getStringArray (R.array.F12),

            resources.getStringArray (R.array.N1),  resources.getStringArray (R.array.N2),
            resources.getStringArray (R.array.N3),  resources.getStringArray (R.array.N4),
            resources.getStringArray (R.array.N5),  resources.getStringArray (R.array.N6),
            resources.getStringArray (R.array.N7),  resources.getStringArray (R.array.N8),
            resources.getStringArray (R.array.N9)
        )

        for (i in 0..20)
        {if(Valkey_23==i){Word_fr_file = Resourses[i]} }


        var Word_int = Word_fr_file.map { it.toInt() }.toTypedArray()

        if (Valkey_4==1){Word_int.reverse()}
        else if (Valkey_4!=0 && Valkey_4!=1)
        {val list = Word_int.toList()
            Collections.rotate(list, Valkey_4)
            Word_int=list.toTypedArray()}

        if (DopKey%2==0)
        {   val list = Word_int.toList()
            Collections.rotate(list, DopKey)
            Word_int=list.toTypedArray()}//крутим
        else if (DopKey%2==1)
        {val list = Word_int.toList()
            Collections.rotate(list, -DopKey)
            Word_int=list.toTypedArray()}//крутим}//крутим


        for (i in 0 until 25) {
            if (Word_int[i]== RedBlue) {
                Gradient_array[i]=1;
            }
            if (Word_int[i]== -RedBlue) {
                Gradient_array[i]=-1;
            }
            if (Word_int[i]== 5) {
                Gradient_array[i]=5;
            }
            if (Word_int[i]== 0) {
                Gradient_array[i]=10;
            }
        }

        var WORDS = resources.getStringArray (R.array.just_words)
        var text_dir : TextView = findViewById(R.id.Text_dir)
        if (Language==1)
        { WORDS = resources.getStringArray (R.array.just_words_ENG)
          WhichTeam.text = " Red team is first"
          SHOW.text = "Show field"
          ChangeWords.text = "Change words"
          text_dir.text = "Remaining words"
            ToMainScreen.text = "To main screen"
        }

        //Помешали слова
        WORDS.shuffle()
        Table.visibility = View.VISIBLE
        //присваеваем слова и анимация

        for (i in 0..24) {
            Allbuttons[i].text = WORDS[i]
        }
        var a= 0

        for (i in 0..4) {
            val anim1 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim1.startOffset = (a + 0).toLong();
            val anim2 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim2.startOffset = (a + 200).toLong();
            val anim3 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim3.startOffset = (a + 400).toLong();
            val anim4 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim4.startOffset = (a + 600).toLong();
            val anim5 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim5.startOffset = (a + 800).toLong();
            val anim6 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
            anim5.startOffset = (a + 1000).toLong();

            Allbuttons[i].startAnimation(anim1);
            Allbuttons[i+5].startAnimation(anim2);
            Allbuttons[i+10].startAnimation(anim3);
            Allbuttons[i+15].startAnimation(anim4);
            Allbuttons[i+20].startAnimation(anim5);

            a += 100;
        }


       // val ChangeWords: Button = findViewById(R.id.ChangeWords)
        ChangeWords.setOnLongClickListener{


            var mediaPlayer: MediaPlayer?
            mediaPlayer= MediaPlayer.create(this,R.raw.tasuem)
            mediaPlayer?.start()

            //достали слова
            var WORDS = resources.getStringArray (R.array.just_words)
            if (Language==1)
            { WORDS = resources.getStringArray (R.array.just_words_ENG)
            }

            //Помешали слова
            WORDS.shuffle()

            var a= 0

            //присваеваем слова
            for (i in 0..24) {
            Allbuttons[i].text = WORDS[i]
            }


            for (i in 0..4) {
                val anim1 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
                anim1.startOffset = (a + 0).toLong();
                val anim2 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
                anim2.startOffset = (a + 200).toLong();
                val anim3 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
                anim3.startOffset = (a + 400).toLong();
                val anim4 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
                anim4.startOffset = (a + 600).toLong();
                val anim5 = AnimationUtils.loadAnimation(this, R.anim.slide_link_recht)
                anim5.startOffset = (a + 800).toLong();

                Allbuttons[i].startAnimation(anim1);
                Allbuttons[i+5].startAnimation(anim2);
                Allbuttons[i+10].startAnimation(anim3);
                Allbuttons[i+15].startAnimation(anim4);
                Allbuttons[i+20].startAnimation(anim5);
                a += 100;
            }



            return@setOnLongClickListener true
        }


        var cap_flag = 1;
        //val SHOW: Button = findViewById(R.id.SHOW)
        SHOW.setOnLongClickListener{

            if (cap_flag ==1) {
            Lock_onClick=1;

                for (k in 0..24) {
                    if (Gradient_array[k] == 1) {
                        Allbuttons[k].setBackgroundResource(R.drawable.red_gradient);
                    }
                    if (Gradient_array[k] == -1) {
                        Allbuttons[k].setBackgroundResource(R.drawable.blue_gradient);
                    }
                    if (Gradient_array[k] == 5) {
                        Allbuttons[k].setBackgroundResource(R.drawable.black_gradient);
                    }
                    if (Gradient_array[k] == 10) {
                        Allbuttons[k].setBackgroundResource(R.drawable.white_gradient);
                    };
                }

            cap_flag =0;
            SHOW.text = "Скрыть поле"
                if (Language==1)
                { SHOW.text =" Hide field"
                }
        }

            else if (cap_flag ==0) {
                Lock_onClick=0;

                for (i in 0..24)
                {
                if (Clicked[i] == 0)
                {Allbuttons[i].setBackgroundResource(R.drawable.default_gradient);};
                }

                cap_flag =1;
                SHOW.text = "Показать поле"
                if (Language==1)
                { SHOW.text = " Show field"
                }
            }

            return@setOnLongClickListener true
        }





        }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {

            putIntArray("Intarray",Clicked)

            val Allbuttons = arrayOf(Word01,Word02, Word03, Word04, Word05,Word06, Word07, Word08,Word09,Word10,Word11,Word12,
                Word13,Word14,Word15,Word16,Word17,Word18,Word19,Word20,Word21,Word22,Word23,Word24,Word25)
            for (k in 0..24)
            { putString(KeyStrings[k], Allbuttons[k].text.toString())}


            //cчетчики команд
            putString("Red", REDcount.text.toString())
            putString("Blue", BLUEcount.text.toString())

            //проверка можно ли мешать

            putString("AllowShuffle", AllowShuffle.toString())

            putString("Lock_onClick", Lock_onClick.toString())
            putString("Lock_onClickplus", Lock_onClick_plus.toString())

        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Clicked = savedInstanceState.getIntArray("Intarray")!!;

        val Allbuttons = arrayOf(Word01,Word02, Word03, Word04, Word05,Word06, Word07, Word08,Word09,Word10,Word11,Word12,
            Word13,Word14,Word15,Word16,Word17,Word18,Word19,Word20,Word21,Word22,Word23,Word24,Word25)

        for (k in 0..24)
        { if (Clicked[k] == 1)
            {
                if (Gradient_array[k]==1){Allbuttons[k].setBackgroundResource(R.drawable.red_gradient)}
                if (Gradient_array[k]==-1){Allbuttons[k].setBackgroundResource(R.drawable.blue_gradient)}
                if (Gradient_array[k]==5){Allbuttons[k].setBackgroundResource(R.drawable.black_gradient)}
                if (Gradient_array[k]==10){Allbuttons[k].setBackgroundResource(R.drawable.white_gradient)};
            };
            Allbuttons[k].text=savedInstanceState?.getString(KeyStrings[k])
        }


        REDcount.text=savedInstanceState?.getString("Red")
        BLUEcount.text=savedInstanceState?.getString("Blue")

        AllowShuffle= (savedInstanceState?.getString("AllowShuffle")?.toInt() ?: super.onRestoreInstanceState(savedInstanceState)) as Int
        if (AllowShuffle==0)
        {ChangeWords.visibility = View.INVISIBLE}

        Lock_onClick= (savedInstanceState?.getString("Lock_onClick")?.toInt() ?: super.onRestoreInstanceState(savedInstanceState)) as Int
        Lock_onClick_plus= (savedInstanceState?.getString("Lock_onClickplus")?.toInt() ?: super.onRestoreInstanceState(savedInstanceState)) as Int


    }

    fun Chekword (view: View) {

    //если подсматриваем слова, нельзя нажимать на них
    if(Lock_onClick==0 && Lock_onClick_plus==0)
    {
        var NUM =0;//возвращяем номер слова и по нему закрашиваем кнопку

        val Allbuttons_id = arrayOf(
            R.id.Word01,R.id.Word02,R.id.Word03, R.id.Word04, R.id.Word05,
            R.id.Word06,R.id.Word07,R.id.Word08, R.id.Word09, R.id.Word10,
            R.id.Word11,R.id.Word12,R.id.Word13,R.id.Word14, R.id.Word15,
            R.id.Word16, R.id.Word17,R.id.Word18,R.id.Word19,R.id.Word20,
            R.id.Word21, R.id.Word22, R.id.Word23,R.id.Word24,R.id.Word25,)

        val Allbuttons = arrayOf(Word01,Word02, Word03, Word04, Word05,Word06, Word07, Word08,Word09,Word10,Word11,Word12,
            Word13,Word14,Word15,Word16,Word17,Word18,Word19,Word20,Word21,Word22,Word23,Word24,Word25)

        for (i in 0..24)
        {
        when (view.id)
        {    Allbuttons_id[i] ->
            {NUM=Check_underWord(Allbuttons[i].resources.getResourceName(view.id))
            if (Gradient_array[NUM]==1){Allbuttons[i].setBackgroundResource(R.drawable.red_gradient);}
            if (Gradient_array[NUM]==-1){Allbuttons[i].setBackgroundResource(R.drawable.blue_gradient);}
            if (Gradient_array[NUM]==5){Allbuttons[i].setBackgroundResource(R.drawable.black_gradient);}
            if (Gradient_array[NUM]==10){Allbuttons[i].setBackgroundResource(R.drawable.white_gradient);}
            }
        }
        }

    }
    }

    fun Backtostart (view: View) {

        val Intent_start = Intent(this, Set_game::class.java)
        startActivity(Intent_start)
        var mediaPlayer= MediaPlayer.create(this,R.raw.grab_cards)
        mediaPlayer?.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null // finish current activity
        }
    }



    fun Check_underWord(Whichword: String): Int {
        var Number = Whichword.takeLast(2)
        var NUM =Number.toInt()//номер слова - он с единицы. А все вычисления с 0
        NUM=NUM-1;
        var EndSound =0;//проверка - чтоб не сработало 2 звука

       if (Gradient_array[NUM]==1 && Clicked[NUM]==0) //чтобы счетчик не срабатывал когда кнопка нажата уже
            {var A=REDcount.text.toString().toInt()
                A=A-1;
                REDcount.text = A.toString()
                if (A==0)
                {   when (Language)
                    {
                    0 -> Toast.makeText(applicationContext, "Красные победили!", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(applicationContext, "Red team won!", Toast.LENGTH_SHORT).show()
                    }
                    Lock_onClick=1
                    EndSound=1;
                    val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.frominvise)
                    ToMainScreen.visibility = View.VISIBLE
                    ToMainScreen.startAnimation(animE_Invis)
                    Lock_onClick_plus=1
                    var mediaPlayer= MediaPlayer.create(this,R.raw.truba_win)
                    mediaPlayer?.start()
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer?.release()
                        mediaPlayer = null // finish current activity
                    }
                }
            }

       if (Gradient_array[NUM]==-1 && Clicked[NUM]==0) //чтобы счетчик не срабатывал когда кнопка нажата уже
            {var A=BLUEcount.text.toString().toInt()
                A=A-1;
                BLUEcount.text = A.toString()

                if (A==0)
                {   when (Language)
                    {
                        0 -> Toast.makeText(applicationContext, "Cиние победили!", Toast.LENGTH_SHORT).show()
                        1 -> Toast.makeText(applicationContext, "Blue team won!", Toast.LENGTH_SHORT).show()
                    }
                    Lock_onClick=1
                    Lock_onClick_plus=1
                    val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.frominvise)
                    ToMainScreen.visibility = View.VISIBLE
                    ToMainScreen.startAnimation(animE_Invis)


                    EndSound=1;
                    var mediaPlayer= MediaPlayer.create(this,R.raw.truba_win)
                    mediaPlayer?.start()
                    mediaPlayer.setOnCompletionListener {
                        mediaPlayer?.release()
                        mediaPlayer = null // finish current activity
                    }
                }
       }

        //проверка что игра окончена

        if (Gradient_array[NUM]==5 && Clicked[NUM]==0)
        {   EndSound=1;
            when (Language)
            {
                0 -> Toast.makeText(applicationContext, "Игра окончена", Toast.LENGTH_SHORT).show()
                1 -> Toast.makeText(applicationContext, "Game over", Toast.LENGTH_SHORT).show()
            }
            Lock_onClick=1
            Lock_onClick_plus=1
            val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.frominvise)
            ToMainScreen.visibility = View.VISIBLE
            ToMainScreen.startAnimation(animE_Invis)
            var mediaPlayer= MediaPlayer.create(this,R.raw.fail_sound)
            mediaPlayer?.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer?.release()
                mediaPlayer = null // finish current activity

            }
        }

        // делаем звук
        if (Clicked[NUM]==0 && EndSound==0){
            var mediaPlayer= MediaPlayer.create(this,R.raw.card_sound)
            mediaPlayer?.start()
            mediaPlayer.setOnCompletionListener {
                mediaPlayer?.release()
                mediaPlayer = null // finish current activity
            }
        }

        //дальше код
        Clicked[NUM]=1

        //Если нажали слово уже нельзя тасовать cлова
        ChangeWords.visibility = View.INVISIBLE

        val animE_Invis = AnimationUtils.loadAnimation(this, R.anim.toinvise)
        if(AllowShuffle==1){ChangeWords.startAnimation(animE_Invis)}


        AllowShuffle=0
        return NUM
    }



    fun toastMe(view: View) {

        var mediaPlayer: MediaPlayer?
        mediaPlayer= MediaPlayer.create(this,R.raw.wrongpress)
        mediaPlayer?.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null // finish current activity
        }


        when (Language)
        {
            0 -> Toast.makeText(applicationContext, "Нужно долгое нажатие", Toast.LENGTH_SHORT).show()
            1 -> Toast.makeText(applicationContext, "Need a long press", Toast.LENGTH_SHORT).show()
        }

    }

    //узнаем Ориаентацию
    private fun getScreenOrientation(): Int {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> 0
            Configuration.ORIENTATION_LANDSCAPE -> 1
            else -> 10
        }
    }

    }
