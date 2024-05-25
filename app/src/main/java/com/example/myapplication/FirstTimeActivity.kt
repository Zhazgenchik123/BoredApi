package com.example.myapplication
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityFirstTimeBinding


class FirstTimeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFirstTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstTimeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        val slides = listOf(
            Slide(R.drawable.help, "Помогать другим"),
            Slide(R.drawable.joy, "Жить в кайф"),
            Slide(R.drawable.selbstentwiclung, "Саморазвитие")
        )
        val adapter = SliderAdapter(slides)
        binding.sliderRecyclerView.adapter = adapter
        binding.sliderRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.button2.setOnClickListener {
           finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)        }
    }
}