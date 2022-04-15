package com.example.rpncalculator

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

    class SettingsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

        private var round_list = arrayOf ("3", "4", "5", "6", "7")
        private var color_list = arrayOf ("white", "green", "blue")
        private var spinner_r:Spinner? = null
        private var spinner_c1:Spinner? = null
        private var spinner_c2:Spinner? = null
        private var round:Int? = null
        private var color1:String? = null
        private var color2:String? = null
        private var round_previous:Int? = null
        private var color1_previous:String? = null
        private var color2_previous:String? = null


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)

        round_previous = intent.getIntExtra("round", 3)
        color1_previous = intent.getStringExtra("bg_color") ?: color1
        color2_previous = intent.getStringExtra("stack_color") ?: color2


        val txt = findViewById<TextView>(R.id.rounding_txt)
        txt.text = "Rounding accuracy: " + round_previous

        spinner_r = findViewById(R.id.spinner_round)
        spinner_r!!.setOnItemSelectedListener(this)

        spinner_c1 = findViewById(R.id.spinner_color1)
        spinner_c1!!.setOnItemSelectedListener(this)

        spinner_c2 = findViewById(R.id.spinner_color2)
        spinner_c2!!.setOnItemSelectedListener(this)

        var array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, round_list)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_r!!.setAdapter(array_adapter)

        array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, color_list)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_c1!!.setAdapter(array_adapter)

        array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, color_list)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_c2!!.setAdapter(array_adapter)

        val saveButton: Button = findViewById(R.id.save)
        saveButton.setOnClickListener{ finish() }

        val cancelButton: Button = findViewById(R.id.cancel)
        cancelButton.setOnClickListener{ cancel() }
    }

    private fun cancel()
    {
        val data = Intent()
        data.putExtra("round", round_previous )
        data.putExtra("bg_color", color1_previous)
        data.putExtra("stack_color", color2_previous)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long)
    {
        if (arg0.getId() == R.id.spinner_round)
        {
            round = Integer.parseInt(round_list[position])
        }
        if (arg0.getId() == R.id.spinner_color1)
        {
            color1 = color_list[position]
        }
        if (arg0.getId() == R.id.spinner_color2)
        {
            color2 = color_list[position]
        }
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }

    override fun finish() {
        val data = Intent()
        data.putExtra("round", round )
        data.putExtra("bg_color", color1)
        data.putExtra("stack_color", color2)
        setResult(Activity.RESULT_OK, data)
        super.finish()
    }
}