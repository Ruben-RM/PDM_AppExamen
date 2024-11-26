package com.example.appexamen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appexamen.MainActivity.Companion.IMG_KEY
import com.example.appexamen.MainActivity.Companion.IMG_ORC
import com.example.appexamen.MainActivity.Companion.IMG_TIEFLING
import com.example.appexamen.MainActivity.Companion.IMG_YUANTI
import com.example.appexamen.MainActivity.Companion.NAME_KEY
import com.example.appexamen.MainActivity.Companion.POWER_KEY
import com.example.appexamen.MainActivity.Companion.RACE_KEY

class CharacterActivity : AppCompatActivity() {

    private lateinit var tv_character_name:TextView
    private lateinit var tv_character_race:TextView
    private lateinit var iv_character_image:ImageView
    private lateinit var tv_character_power:TextView
    private lateinit var btn_back2Main:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_character)

        initComponents()
        initListeners()
        initUI()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initComponents()
    {
        tv_character_name = findViewById(R.id.tv_character_name)
        tv_character_race = findViewById(R.id.tv_character_race)
        iv_character_image = findViewById(R.id.iv_character_image)
        tv_character_power = findViewById(R.id.tv_character_power)
        btn_back2Main = findViewById(R.id.btn_back2main)
    }

    private fun initListeners()
    {
        btn_back2Main.setOnClickListener()
        {
            nav2Main()
        }
    }

    private fun nav2Main()
    {
        val intentMA = Intent(this, MainActivity::class.java)

        startActivity(intentMA)
    }

    private fun initUI()
    {
        setName()
        setRace()
        setImage()
        setPower()
    }

    private fun setName()
    {
        val nombre = intent.extras?.getString(NAME_KEY) ?: ""
        tv_character_name.text = "Name: $nombre"
    }

    private fun setRace()
    {
        val race = intent.extras?.getString(RACE_KEY) ?: ""
        tv_character_race.text = "Race: $race"
    }

    private fun setImage()
    {
        val image = intent.extras?.getString(IMG_KEY) ?: ""

        val drawable = when(image)
        {
            IMG_YUANTI -> R.drawable.yuanti
            IMG_ORC -> R.drawable.orc
            IMG_TIEFLING -> R.drawable.tiefling

            else -> R.drawable.orc
        }

        iv_character_image.setImageDrawable(ContextCompat.getDrawable(this, drawable))
    }

    private fun setPower()
    {
        val power = intent.extras?.getInt(POWER_KEY) ?: 50
        tv_character_power.text = "Power: $power"
    }
}