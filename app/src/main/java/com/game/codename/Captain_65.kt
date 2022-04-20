package com.game.codename

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import java.io.*
import java.util.*

class Captain_65 : AppCompatActivity() {
    //обьявляем переменные
    private lateinit var Table: TableLayout

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

    private lateinit var Word26: Button
    private lateinit var Word27: Button
    private lateinit var Word28: Button
    private lateinit var Word29: Button
    private lateinit var Word30: Button
    private lateinit var Toolbar: Toolbar
    var TYPE=25;

    val Red = IntArray(30)
    val G = IntArray(30)
    val B = IntArray(30)
    companion object{
        const val KEY ="KEY"
    }


    var FileLan = "language.txt"
    var FileLanguage = File("/data/data/com.Sp.code.game.codename/files/" + File.separator + FileLan)
    var Language = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_captain65)

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

        //тулбар
        Toolbar = findViewById(R.id.atoolbar)
        setSupportActionBar(Toolbar)
        var Direction: TextView = findViewById(R.id.Text_direction)
        Toolbar.title = "Поле для капитанов:"
        if (Language==1)
        {  Toolbar.title = "Field for captains";
            Direction.text ="Top"
        }


        //находим переменные
        Table = findViewById(R.id.Table)
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

        Word26= findViewById(R.id.Word26);Word27= findViewById(R.id.Word27)
        Word28= findViewById(R.id.Word28);Word29= findViewById(R.id.Word29)
        Word30= findViewById(R.id.Word30)

        //раскладываем входное число на значения
        val KEY_1 = this.intent.getIntExtra(MainActivity.KEY, 0)
        val Valkey_1 = (KEY_1%10)%2; // последняя цифра показывает инверсию
        val Valkey_23 = (KEY_1%1000-Valkey_1)%20;  //цифры  23 это номер массива
        val Valkey_4 = KEY_1/1000; //цифра 4 - это номер поворота

        val Valkey_65 = (KEY_1%1000-Valkey_1)%5;//НОВАЯ СТРОКА
        val DopKey = (Valkey_1+Valkey_23+Valkey_4)%3

        var RedBlue =1;//переменная для того чтобы поменять цвет
        if(Valkey_1==0){RedBlue=-1} //меняем цвет по остатку - последняя цифра


        var Word_fr_file: Array<String> =emptyArray()
        var Word_65: Array<String> =emptyArray()

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

        //добавка еще одного ряда
        if(Valkey_65==0){Word_65 = resources.getStringArray (R.array.X30_1)}
        if(Valkey_65==1){Word_65 = resources.getStringArray (R.array.X30_2)}
        if(Valkey_65==2){Word_65 = resources.getStringArray (R.array.X30_3)}
        if(Valkey_65==3){Word_65 = resources.getStringArray (R.array.X30_4)}
        if(Valkey_65==4){Word_65 = resources.getStringArray (R.array.X30_5)}

        var Word_int: Array<Int> = Word_fr_file.map { it.toInt() }.toTypedArray()
        var Word_65_int: Array<Int> = Word_65.map { it.toInt() }.toTypedArray()

        if (Valkey_4==1){Word_int.reverse()}//крутим
        else if (Valkey_4!=0 && Valkey_4!=1)
        {val list = Word_int.toList()
            Collections.rotate(list, Valkey_4)
            Word_int=list.toTypedArray()
        }

        if (DopKey%2==0)
        {   val list = Word_int.toList()
            Collections.rotate(list, DopKey)
            Word_int=list.toTypedArray()}//крутим
        else if (DopKey%2==1)
        {val list = Word_int.toList()
            Collections.rotate(list, -DopKey)
            Word_int=list.toTypedArray()}//крутим}//крутим

        var Word_all = Word_int.plus(Word_65_int)
        if (DopKey%2==0)
        {   val list = Word_all.toList()
            Collections.rotate(list, DopKey+6)
            Word_all=list.toTypedArray()}//крутим
        else if (DopKey%2==1)
        {val list = Word_all.toList()
            Collections.rotate(list, -DopKey-6)
            Word_all=list.toTypedArray()}//крутим}//крутим


        for (i in 0 until 30) {
            if (Word_all[i]== RedBlue) {
                Red[i]=255;G[i]=0;B[i]=0
            }
            if (Word_all[i]== -RedBlue) {
                Red[i]=0;G[i]=0;B[i]=255
            }
            if (Word_all[i]== 5) {
                Red[i]=0;G[i]=0;B[i]=0
            }
            if (Word_all[i]== 0) {
                Red[i]=235;G[i]=235;B[i]=180
            }
        }
        var a= 0
        Table.visibility = View.VISIBLE


        val Allbuttons = arrayOf(Word01,Word02, Word03, Word04, Word05,Word06, Word07, Word08,Word09,Word10,Word11,Word12,
            Word13,Word14,Word15,Word16,Word17,Word18,Word19,Word20,Word21,Word22,Word23,Word24,Word25,Word26,Word27,Word28,Word29,Word30)

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
            Allbuttons[i+25].startAnimation(anim5);
            a += 100;
        }


        for (i in 0..29)
        {Allbuttons[i].backgroundTintList = ColorStateList.valueOf(Color.rgb(Red[i],G[i],B[i]));}

        Toolbar.setOnMenuItemClickListener {
            when(it.itemId) {

                R.id.GoBACK ->
                {
                    //возвращаемся назад
                    val Intent1 = Intent(this, Set_game::class.java)
                    Intent1.putExtra(Set_game.Field_SIZE,TYPE)

                    startActivity(Intent1)//зашли на экран настроек
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
        menuInflater.inflate(R.menu.set_menu_2,menu)
        return true
    }

}