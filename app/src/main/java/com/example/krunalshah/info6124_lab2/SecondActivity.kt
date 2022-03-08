package com.example.krunalshah.info6124_lab2

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup

class SecondActivity : AppCompatActivity() {
    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton
    var myList = ArrayList<String>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val kitchenTiles = findViewById<EditText>(R.id.tilesEditText)
        val button: Button = findViewById(R.id.closeActivityButton)
        val radioGroup: RadioGroup = findViewById(R.id.kitchenType)

//        val data = ArrayList<ItemsViewModel>()
//        val adapter = myAdapter(data)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)

            val selectedOption: Int = radioGroup.checkedRadioButtonId
            radioButton = findViewById(selectedOption)

            val tiles: String = kitchenTiles.text.toString()
            val type: String = radioButton.text.toString()

            var properties = "Tiles: " + tiles + " Type: " + type

            intent.putExtra("message", properties)
            startActivity(intent)
        }
    }
}