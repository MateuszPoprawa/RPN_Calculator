package com.example.rpncalculator

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.concurrent.CopyOnWriteArrayList

class MainActivity : AppCompatActivity() {

    private var round = 3
    private val REQUEST_CODE = 10000
    private var bg_color = "white"
    private var stack_color = "white"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val switchToSettings: Button = findViewById(R.id.settings_button);
        switchToSettings.setOnClickListener{ showSettings() }
    }

    private fun showSettings()
    {
        val i = Intent(this, SettingsActivity::class.java)
        i.putExtra("round", round)
        i.putExtra("bg_color", bg_color)
        i.putExtra("stack_color", stack_color)
        startActivityForResult(i, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            round = data?.getIntExtra("round", 3) ?: round
            bg_color = data?.getStringExtra("bg_color") ?: bg_color
            stack_color = data?.getStringExtra("stack_color") ?: stack_color

            val l = findViewById<ConstraintLayout>(R.id.Layout)
            change_color(l, bg_color)

            var text = findViewById<TextView>(R.id.textView1)
            change_color(text, stack_color)
            text = findViewById<TextView>(R.id.textView2)
            change_color(text, stack_color)
            text = findViewById<TextView>(R.id.textView3)
            change_color(text, stack_color)
            text = findViewById<TextView>(R.id.textView4)
            change_color(text, stack_color)
        }
    }

    fun change_color(l: ConstraintLayout, color:String)
    {
        if (color == "white")
            l.setBackgroundColor(Color.WHITE)
        else if (color == "green")
            l.setBackgroundColor(Color.GREEN)
        else if (color == "blue")
            l.setBackgroundColor(Color.BLUE)
    }

    fun change_color(l: TextView, color:String)
    {
        if (color == "white")
            l.setBackgroundColor(Color.WHITE)
        else if (color == "green")
            l.setBackgroundColor(Color.GREEN)
        else if (color == "blue")
            l.setBackgroundColor(Color.BLUE)
    }
}