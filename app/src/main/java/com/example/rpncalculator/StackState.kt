package com.example.rpncalculator

import java.util.*

class StackState {
    var stack:Stack<String> = Stack()
    var text1:String = " "
    var text2:String = " "
    var text3:String = " "
    var text4:String = " "
    var dot:Boolean = false
    var sign: Boolean = false

    @Suppress("UNCHECKED_CAST")
    fun saveState(st:Stack<String>, d:Boolean, s:Boolean, t1:String, t2:String, t3:String, t4:String)
    {
        stack = st.clone() as Stack<String>
        dot = d
        sign = s
        text1 = t1
        text2 = t2
        text3 = t3
        text4 = t4
    }
}