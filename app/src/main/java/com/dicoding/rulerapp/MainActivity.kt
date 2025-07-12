package com.dicoding.rulerapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView


    private val minValue = 60
    private val maxValue = 250
    private val defaultValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rulerRecyclerView)
//        mengambil view value (tinggi : 165)
        val valueText = findViewById<TextView>(R.id.valueText)


        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = RulerAdapter(minValue, maxValue)

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val centerView = snapHelper.findSnapView(layoutManager)
                    val pos = layoutManager.getPosition(centerView!!)
                    val value = minValue + pos
                    valueText.text = value.toString()
                }
            }
        })

        recyclerView.post {
            recyclerView.scrollToPosition(defaultValue - minValue)
            valueText.text = defaultValue.toString()
        }
    }
}