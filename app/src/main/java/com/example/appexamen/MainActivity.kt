package com.example.appexamen

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import com.google.android.material.snackbar.Snackbar
import org.w3c.dom.Text

class MainActivity: AppCompatActivity()
{

    private var name:String = ""
    private lateinit var et_name:EditText
    private lateinit var cv_character_1:CardView
    private lateinit var cv_character_2:CardView
    private lateinit var cv_character_3:CardView
    private var race:String = ""
    private var characterSelected = ""
    private lateinit var tv_power:TextView
    private lateinit var rs_power:RangeSlider
    private var power:Int = 50
    private lateinit var btn_createCharacter:FloatingActionButton

    companion object{
        const val NAME_KEY = "NAME"
        const val RACE_KEY = "RACE"
        const val IMG_KEY = "IMG"
        const val POWER_KEY = "POWER"
        const val IMG_YUANTI = "yuanti"
        const val IMG_ORC = "orc"
        const val IMG_TIEFLING = "tiefling"
        const val RACE_YUANTI = "Yuanti"
        const val RACE_ORC = "Orc"
        const val RACE_TIEFLING = "Tiefling"
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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
        et_name = findViewById(R.id.et_name)
        cv_character_1 = findViewById(R.id.cv_character_yuanti)
        cv_character_2 = findViewById(R.id.cv_character_orc)
        cv_character_3 = findViewById(R.id.cv_character_tiefling)
        tv_power = findViewById(R.id.tv_power)
        rs_power = findViewById(R.id.rs_power)
        btn_createCharacter = findViewById(R.id.btn_createCharacter)
    }

    private fun initListeners()
    {
        cv_character_1.setOnClickListener()
        {
            characterSelected = IMG_YUANTI
            setCardBackgrounds()
        }
        cv_character_2.setOnClickListener()
        {
            characterSelected = IMG_ORC
            setCardBackgrounds()
        }
        cv_character_3.setOnClickListener()
        {
            characterSelected = Companion.IMG_TIEFLING
            setCardBackgrounds()
        }
        rs_power.addOnChangeListener()
        { _, value, _ ->
            power = value.toInt()
            setTVPower()
        }
        btn_createCharacter.setOnClickListener()
        {
            name = et_name.text.toString()

            if(fieldsAreValid())
                nav2CharacterScreen()
        }
    }

    private fun initUI()
    {
        initPower()
    }

    private fun setCardBackgrounds()
    {
        when(characterSelected)
        {
            IMG_YUANTI ->
            {
                race = RACE_YUANTI
                cv_character_1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.bg_card_selected))
                setNonSelectedCardBackgrounds(cv_character_2, cv_character_3)
            }
            IMG_ORC ->
            {
                race = RACE_ORC
                cv_character_2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.bg_card_selected))
                setNonSelectedCardBackgrounds(cv_character_1, cv_character_3)
            }
            IMG_TIEFLING ->
            {
                race = RACE_TIEFLING
                cv_character_3.setCardBackgroundColor(ContextCompat.getColor(this, R.color.bg_card_selected))
                setNonSelectedCardBackgrounds(cv_character_1, cv_character_2)
            }
        }
    }

    private fun setNonSelectedCardBackgrounds(cv1:CardView, cv2:CardView)
    {
        cv1.setCardBackgroundColor(ContextCompat.getColor(this, R.color.bg_card))
        cv2.setCardBackgroundColor(ContextCompat.getColor(this, R.color.bg_card))
    }

    private fun initPower()
    {
        rs_power.values = List(1){50.0f}
        setTVPower()
    }

    private fun setTVPower()
    {
        tv_power.text = "Power: $power"
    }

    private fun nav2CharacterScreen()
    {
        val intentCA = Intent(this, CharacterActivity::class.java)

        intentCA.putExtra(NAME_KEY, name)
        intentCA.putExtra(RACE_KEY, race)
        intentCA.putExtra(IMG_KEY, characterSelected)
        intentCA.putExtra(POWER_KEY, power)

        startActivity(intentCA)
    }

    private fun fieldsAreValid():Boolean
    {
        var validFields = true

        if(name == "")
        {
            Toast.makeText(this, "Name Field must not be empty!", Toast.LENGTH_SHORT).show()
            validFields = false
        }

        if(race == "")
        {
            Snackbar.make(cv_character_1, "You must select a race!", Snackbar.LENGTH_SHORT).setAnchorView(cv_character_1).show()
            validFields = false
        }

        return validFields
    }
}