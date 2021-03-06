package com.game.codename

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import java.io.*

private lateinit var RULES: TextView
private lateinit var toolbar: Toolbar





var FileLan = "language.txt"
var FileLanguage = File("/data/data/com.Sp.code.game.codename/files/" + File.separator + FileLan)
var Language = 0


class Rules : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rules)
        RULES=findViewById(R.id.rules)

        RULES.movementMethod = ScrollingMovementMethod();

        toolbar = findViewById(R.id.atoolbar)
        setSupportActionBar(toolbar)

        toolbar.title = "Правила игры:"

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
        { toolbar.title = "Rules:"
            RULES.text = resources.getString(R.string.Rules_ENG);
            }
    }


    //menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.set_menu_2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.GoBACK) {

            val i = Intent(this, start_screen::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)


            val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.shufflesound)
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener { mp -> mp.release() }
        }
        return true
    }
}