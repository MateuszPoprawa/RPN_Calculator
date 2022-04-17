package com.example.rpncalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

    class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

        private var roundList = arrayOf ("3", "4", "5", "6", "7")
        private var colorList = arrayOf ("white", "green", "blue")
        private var spinnerR:Spinner? = null
        private var spinnerC1:Spinner? = null
        private var spinnerC2:Spinner? = null
        private var round:Int? = null
        private var color1:String? = null
        private var color2:String? = null
        private var roundPrevious:Int? = null
        private var color1Previous:String? = null
        private var color2Previous:String? = null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        roundPrevious = intent.getIntExtra("round", 3)
        color1Previous = intent.getStringExtra("bg_color") ?: color1
        color2Previous = intent.getStringExtra("stack_color") ?: color2


        //val txt = findViewById<TextView>(R.id.rounding_txt)
        //txt.text = "Rounding accuracy: " + round_previous

        spinnerR = findViewById(R.id.spinner_round)
        spinnerR!!.onItemSelectedListener = this

        spinnerC1 = findViewById(R.id.spinner_color1)
        spinnerC1!!.onItemSelectedListener = this

        spinnerC2 = findViewById(R.id.spinner_color2)
        spinnerC2!!.onItemSelectedListener = this

        var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roundList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerR!!.adapter = arrayAdapter

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerC1!!.adapter = arrayAdapter

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, colorList)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerC2!!.adapter = arrayAdapter

        val saveButton: Button = findViewById(R.id.save)
        saveButton.setOnClickListener{ finish() }

        val cancelButton: Button = findViewById(R.id.cancel)
        cancelButton.setOnClickListener{ cancel() }
    }

    private fun cancel()
    {
        val data = Intent()
        data.putExtra("round", roundPrevious )
        data.putExtra("bg_color", color1Previous)
        data.putExtra("stack_color", color2Previous)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long)
    {

        if(arg0.id == R.id.spinner_round)
            round = Integer.parseInt(roundList[position])
        if(arg0.id == R.id.spinner_color1)
            color1 = colorList[position]
        if(arg0.id == R.id.spinner_color2)
            color2 = colorList[position]


    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    override fun finish() {
        val data = Intent()
        data.putExtra("round", round )
        data.putExtra("bgColor", color1)
        data.putExtra("stackColor", color2)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }
}