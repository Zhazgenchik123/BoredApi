package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SliderAdapter(private val slides: List<Slide>) : RecyclerView.Adapter<SliderAdapter.SlideViewHolder>() {

    inner class SlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.slider_item, parent, false)
        return SlideViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        val currentSlide = slides[position]
        holder.imageView.setImageResource(currentSlide.imageRes)
        holder.textView.text = currentSlide.text
    }

    override fun getItemCount() = slides.size
}

data class Slide(val imageRes: Int, val text: String)
