package com.game.codename

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import java.io.*

class start_screen : AppCompatActivity() {
    private lateinit var imp: Button
    private lateinit var Rules: Button
    private lateinit var Spinner: Spinner
    private lateinit var adapter: spinadapter

    var images = intArrayOf(R.drawable.ru, R.drawable.uk)
    var ButText = arrayOf("Начать игру", "Start Game")
    var RulesButText = arrayOf("Правила", "Rules")

    var Current_language = 0

    //для типа локалицации файл
    var FileLan = "language.txt"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_screen)

        Spinner = findViewById<Spinner>(R.id.spinner_l)
        imp = findViewById<Button>(R.id.imp)
        Rules = findViewById<Button>(R.id.rules)


        val res = resources
        val Languages = res.getStringArray(R.array.Language)



        adapter = spinadapter(this, Languages, images)
        Spinner.adapter = adapter

        Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                imp.text = ButText[i]
                Rules.text = RulesButText[i]
                Current_language = i
                try {
                    // отрываем поток для записи
                    val bw =
                        BufferedWriter(OutputStreamWriter(openFileOutput(FileLan, MODE_PRIVATE)))
                    bw.write(Current_language.toString() + "\n")
                    bw.close()
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }


    }


    fun On_clickk(view: View ) {
        val mediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.grab_cards)
        when (view.id) {
            R.id.imp -> {
                // imp.setBackgroundColor(Color.RED);
                val intent = Intent(this, Set_game::class.java)
                startActivity(intent)
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp -> mp.release() })
            }

            R.id.rules -> {
                val intent = Intent(this, com.game.codename.Rules::class.java)
                startActivity(intent)
                mediaPlayer.start()
                mediaPlayer.setOnCompletionListener(MediaPlayer.OnCompletionListener { mp -> mp.release() })
            }
            }
    }

}