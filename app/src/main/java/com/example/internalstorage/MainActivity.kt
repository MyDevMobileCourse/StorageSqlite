package com.example.internalstorage

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    lateinit var root:ConstraintLayout
    lateinit var counter:TextView
    lateinit var red:Button
    lateinit var green:Button
    lateinit var blue:Button
    lateinit var black:Button
    lateinit var white:Button
    lateinit var count:Button
    lateinit var save:Button
    lateinit var clear:Button
    lateinit var load:Button
    lateinit var sharedPref:SharedPref
    var color = Color.WHITE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    fun init(){
        root = findViewById(R.id.root)
        counter = findViewById(R.id.counter)
        red = findViewById(R.id.red)
        green = findViewById(R.id.green)
        blue = findViewById(R.id.blue)
        black = findViewById(R.id.black)
        white = findViewById(R.id.white)
        count = findViewById(R.id.count)
        save = findViewById(R.id.save)
        clear = findViewById(R.id.clear)
        load = findViewById(R.id.load2)
        red.setOnClickListener {
            root.setBackgroundColor(Color.RED)
            color = Color.RED
        }
        green.setOnClickListener {
            root.setBackgroundColor(Color.GREEN)
            color = Color.GREEN
        }
        blue.setOnClickListener {
            root.setBackgroundColor(Color.BLUE)
            color = Color.BLUE
        }
        black.setOnClickListener {
            root.setBackgroundColor(Color.BLACK)
            color = Color.BLACK
        }
        white.setOnClickListener {
            root.setBackgroundColor(Color.WHITE)
            color = Color.WHITE
        }
        count.setOnClickListener {
            var count = counter.text.toString().toInt()
            count++
            counter.text = count.toString()
        }
        save.setOnClickListener {
            sharedPref = SharedPref(this)
            sharedPref.save("counter",counter.text.toString())
            sharedPref.save("color",color)
        }
        clear.setOnClickListener {
            sharedPref = SharedPref(this)
            sharedPref.clearSharedPreference()
        }
        load.setOnClickListener {
            sharedPref = SharedPref(this)
            counter.text = sharedPref.getValueInt("counter")
            root.setBackgroundColor(sharedPref.getValueInt("color"))
        }


    }
}