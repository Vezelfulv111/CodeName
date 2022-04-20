package com.game.codename

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.game.codename.Set_game.Companion.Field_SIZE
import java.io.*

class Settings : AppCompatActivity() {
    private lateinit var Toolbar: Toolbar

    private lateinit var radio25: RadioButton
    private lateinit var radio30: RadioButton

    private lateinit var TextDesc: TextView

    private lateinit var radioGroup: RadioGroup

    var TYPE = 25;
    var Field_SIZE_1=25;
    var FileLan = "language.txt"
    var FileLanguage = File("/data/data/com.Sp.code.game.codename/files/" + File.separator + FileLan)
    var Language = 0

    companion object{
        const val Back_size ="Back_size"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        Toolbar = findViewById(R.id.atoolbar)
        setSupportActionBar(Toolbar)

        radio25 = findViewById(R.id.radio25)
        radio30 = findViewById(R.id.radio30)
        radioGroup= findViewById(R.id.radioGroup)

        TextDesc=findViewById(R.id.textDesc)


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

        var Heading: TextView = findViewById(R.id.FieldTopSize)
        var Lol = resources.getString(R.string.Description_25)
        if (Language==1)
        {   Lol = resources.getString(R.string.Description_25_ENG)
          Heading.text="Field size"
        }
        TextDesc.text = Lol
        //взяли значение с старт экрана -если поле 6на5 поменяли
        //проверка языка

        Field_SIZE_1 = this.intent.getIntExtra(Back_size, 25)
        if(Field_SIZE_1==30)
        {
            radio30.isChecked = true
            var Lol = resources.getString(R.string.Description_30)
            if (Language==1)
            {   Lol = resources.getString(R.string.Description_30_ENG)
            }
            TextDesc.text = Lol
            }


        Toolbar.setOnMenuItemClickListener {
            when(it.itemId) {

                R.id.GoBACK ->
                {
                //возвращаемся назад
                    val Intent1 = Intent(this, Set_game::class.java)
                    Intent1.putExtra(Field_SIZE,TYPE)

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


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            val checked = view.isChecked
            // Check which radio button was clicked
            when (view.getId()) {
                R.id.radio25 ->
                    if (checked) {
                        //Toast.makeText(this, radio30.isChecked.toString(), Toast.LENGTH_SHORT).show()
                        TYPE=25;
                        var Lol = resources.getString(R.string.Description_25)
                        if (Language==1)
                        {   Lol = resources.getString(R.string.Description_25_ENG)
                        }
                        TextDesc.text = Lol
                    }
                R.id.radio30 ->
                    if (checked) {
                        //Toast.makeText(this, radio30.isChecked.toString(), Toast.LENGTH_SHORT).show()
                        TYPE=30;
                        var Lol = resources.getString(R.string.Description_30)
                        if (Language==1)
                        {   Lol = resources.getString(R.string.Description_30_ENG)
                        }
                        TextDesc.text = Lol

                        //resources.getStringArray (R.array.F1)
                    }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {

            putInt("TYPE_State",TYPE)


        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {

        TYPE=savedInstanceState?.getInt("TYPE_State")



        super.onRestoreInstanceState(savedInstanceState)
    }
}
