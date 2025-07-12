package com.dicoding.rulerapp

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RulerAdapter(private val minValue: Int, private val maxValue: Int) :
    RecyclerView.Adapter<RulerAdapter.RulerViewHolder>() {

    inner class RulerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tick: View = view.findViewById(R.id.tick)
        val label: TextView = view.findViewById(R.id.label)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RulerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ruler, parent, false)
        return RulerViewHolder(view)
    }

    override fun getItemCount(): Int = maxValue - minValue + 1

    override fun onBindViewHolder(holder: RulerViewHolder, position: Int) {
        val value = minValue + position

        if (value % 10 == 0) {
            holder.label.text = value.toString()
            holder.label.visibility = View.VISIBLE

            // garis lebih tinggi dan lebih tebal
            holder.tick.layoutParams.height = 120
            holder.tick.layoutParams.width = 8
            holder.tick.setBackgroundColor(Color.parseColor("#5D6A85"))
        } else {
            holder.label.visibility = View.GONE

            // garis normal
            holder.tick.layoutParams.height = 80
            holder.tick.layoutParams.width = 4
            holder.tick.setBackgroundColor(Color.parseColor("#BEC5D2"))
        }

        holder.tick.requestLayout()
    }

}