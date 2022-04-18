package com.example.rpncalculator

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.util.Stack
import kotlin.math.pow
import kotlin.math.round
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    private var round = 3
    private val REQUEST_CODE = 10000
    private var bgColor = "white"
    private var stackColor = "white"
    private lateinit var text1:TextView
    private lateinit var text2:TextView
    private lateinit var  text3:TextView
    private lateinit var text4:TextView
    private var stack:Stack<String> = Stack()
    private var number:String = " "
    private var dot:Boolean = false
    private var sign: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        text1 = findViewById(R.id.textView1)
        text2 = findViewById(R.id.textView2)
        text3 = findViewById(R.id.textView3)
        text4 = findViewById(R.id.textView4)

        val switchToSettings: Button = findViewById(R.id.settings_button)
        switchToSettings.setOnClickListener{ showSettings() }

        buttons()

    }

    private fun buttons()
    {
        findViewById<Button>(R.id.button0).setOnClickListener{enterNumber('0')}

        findViewById<Button>(R.id.button1).setOnClickListener{ enterNumber('1')}

        findViewById<Button>(R.id.button2).setOnClickListener{ enterNumber('2')}

        findViewById<Button>(R.id.button3).setOnClickListener{ enterNumber('3')}

        findViewById<Button>(R.id.button4).setOnClickListener{ enterNumber('4')}

        findViewById<Button>(R.id.button5).setOnClickListener{ enterNumber('5')}

        findViewById<Button>(R.id.button6).setOnClickListener{ enterNumber('6')}

        findViewById<Button>(R.id.button7).setOnClickListener{ enterNumber('7')}

        findViewById<Button>(R.id.button8).setOnClickListener{ enterNumber('8')}

        findViewById<Button>(R.id.button9).setOnClickListener{ enterNumber('9')}

        findViewById<Button>(R.id.button_dot).setOnClickListener{ enterNumber('.')}

        findViewById<Button>(R.id.button_sign).setOnClickListener{
            sign = sign.not()
            if(sign)
            {
                number = number.replaceRange(0, 1, "-")
                text1.text = number
                Log.i("i1", number)
            }
            else
            {
                number = number.replaceRange(0, 1, " ")
                text1.text = number
            }
        }

        findViewById<Button>(R.id.button_enter).setOnClickListener {  enter() }

        findViewById<Button>(R.id.button_plus).setOnClickListener { operation('+') }

        findViewById<Button>(R.id.button_minus).setOnClickListener { operation('-') }

        findViewById<Button>(R.id.button_mul).setOnClickListener { operation('*') }

        findViewById<Button>(R.id.button_div).setOnClickListener { operation('/') }

        findViewById<Button>(R.id.button_pow).setOnClickListener { operation('^') }

        findViewById<Button>(R.id.button_sqrt).setOnClickListener { operation('s') }

        findViewById<Button>(R.id.button_swap).setOnClickListener {
            if( stack.empty() || number == " " || number == "-")
                return@setOnClickListener
            val x = stack.pop()
            stack.push(number)
            text2.text = number
            number = x
            text1.text = number
            sign = number.toDouble() < 0
            dot = number.indexOf('.') != -1
        }

        findViewById<Button>(R.id.button_drop).setOnClickListener {
            number = " "
            dot = false
            sign = false
            text1.text = number
        }

        findViewById<Button>(R.id.button_AC).setOnClickListener {
            text1.text = " "
            text2.text = " "
            text3.text = " "
            text4.text = " "
            number = " "
            dot = false
            sign = false
            stack.clear()
        }

        findViewById<Button>(R.id.button_undo).setOnClickListener {

        }

        findViewById<Button>(R.id.button_bs).setOnClickListener {
            if ( number.length > 1 )
            {
                val c = number[number.length-1]
                number = number.substring(0, number.length-1)
                text1.text = number
                if(c == '.')
                    dot = false
            }
        }
    }

    private fun enterNumber(c:Char)
    {
        if(!(c == '.' && dot))
        {
            number += c
            text1.text = number
            if(c == '.')
                dot = true
        }
    }

    private fun enter()
    {
        if(number == " " || number == "-")
        {
            if(!stack.empty())
            {
                val copy = stack.peek()
                number = copy
                text1.text = copy
            }
        }
        else
        {
            stack.push(number)
            text4.text = text3.text
            text3.text = text2.text
            text2.text = text1.text
            text1.text = " "
            number = " "
            dot = false
            sign = false
        }
    }

    private fun roundResult(y:Double): String {
        val z:Double = 10.0.pow(round.toDouble())
        val r:Double = round(y*z)/z
        return r.toString()
    }

    private fun operation(c:Char)
    {
        if(stack.size < 1 || number == " " || number == "-" || c == 's')
        {
            if ( c == 's' && !sign && number.length > 1)
            {
                val y:Double = sqrt(number.toDouble())
                number = roundResult(y)
                text1.text = number
            }
            return
        }
        val x2 =  stack.pop().toDouble()
        val x1 = number.toDouble()
        var y = 0.0
        if(c == '+')
            y = x2 + x1
        if(c == '-')
            y= x2 - x1
        if(c == '*')
            y = x2 * x1
        if(c == '/')
        {
            if(x1 != 0.0)
            {
                y = x2 / x1
            }
            else
            {
                stack.push(x2.toString())
                return
            }
        }
        if(c == '^')
            y = x2.pow(x1)
        if (!stack.empty())
        {
            val x3 = stack.pop()
            text2.text = x3
            if (!stack.empty())
            {
                val x4 = stack.pop()
                text3.text = x4

                if (!stack.empty())
                {
                    val x5 = stack.peek()
                    text4.text = x5
                }
                else
                {
                    text4.text = " "
                }
                stack.push(x4)
            }
            else
            {
                text3.text = " "
            }
            stack.push(x3)
        }
        else
        {
            text2.text = " "
        }
        number = roundResult(y)
        if(y > 0)
            number = " $number"
        text1.text = number
        dot = true
        sign = y < 0
    }

    private fun showSettings()
    {
        val i = Intent(this, SettingsActivity::class.java)
        i.putExtra("round", round)
        i.putExtra("bg_color", bgColor)
        i.putExtra("stack_color", stackColor)
        startActivityForResult(i, REQUEST_CODE)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            round = data?.getIntExtra("round", 3) ?: round
            bgColor = data?.getStringExtra("bgColor") ?: bgColor
            stackColor = data?.getStringExtra("stackColor") ?: stackColor

            val l = findViewById<ConstraintLayout>(R.id.Layout)
            changeColor(l, bgColor)

            changeColor(text1, stackColor)
            changeColor(text2, stackColor)
            changeColor(text3, stackColor)
            changeColor(text4, stackColor)
            changeColor(findViewById<TextView>(R.id.textView5), stackColor)
            changeColor(findViewById<TextView>(R.id.textView6), stackColor)
            changeColor(findViewById<TextView>(R.id.textView7), stackColor)
            changeColor(findViewById<TextView>(R.id.textView8), stackColor)
        }

    }

    private fun changeColor(l: ConstraintLayout, color:String)
    {
        when(color)
        {
            "white" -> l.setBackgroundColor(Color.WHITE)
            "green" -> l.setBackgroundColor(Color.GREEN)
            "blue" -> l.setBackgroundColor(Color.BLUE)
        }
    }

    private fun changeColor(l: TextView, color:String)
    {
        when(color) {
            "white" -> l.setBackgroundColor(Color.WHITE)
            "green" -> l.setBackgroundColor(Color.GREEN)
            "blue" -> l.setBackgroundColor(Color.BLUE)
        }
    }
}