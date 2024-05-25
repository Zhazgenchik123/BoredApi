package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var bindingclass : ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("Mypref", Context.MODE_PRIVATE)

        var  isFirstTime = sharedPreferences.getBoolean("isFirstTime", true)

        bindingclass = ActivityMainBinding.inflate(layoutInflater)
        Log.d("whatis", "$isFirstTime")
        enableEdgeToEdge()


        if(isFirstTime){
            val intent = Intent(this, FirstTimeActivity::class.java)
            sharedPreferences.edit().putBoolean("isFirstTime", false).apply()
            startActivityForResult(intent, 1)
            Log.d("whatis", "$isFirstTime")



        }else{
            setContentView(bindingclass.root)
        }
        bindingclass.button.setOnClickListener {
            bindingclass.tv.text = "Activity: "
            bindingclass.tv2.text = "Type: "
            bindingclass.textView3.text = "Participants: "
            bindingclass.textView4.text = "Price: "

            bindingclass.textView6.text = "Key: "
            bindingclass.textView7.text = "Accessibility: "

            val call = ApiClient.create().getActivity()
            call.enqueue(object : Callback<ActivityResponse> {
                override fun onResponse(call: Call<ActivityResponse>, response: Response<ActivityResponse>) {

                    if (response.isSuccessful) {
                        val activity = response.body()?.activity ?: "No activity found"
                        val type = response.body()?.type ?: "No type found"
                        val participants =response.body()?.participants ?: "No participants found"
                        val price =response.body()?.price ?: "No price found"

                        val key =response.body()?.key ?: "No key found"
                        val accessibility =response.body()?.accessibility ?: "No accessibility found"
                        bindingclass.tv.text = bindingclass.tv.text.toString() + activity
                        bindingclass.tv2.text = bindingclass.tv2.text.toString()+type
                        bindingclass.textView3.text = bindingclass.textView3.text.toString()+ participants.toString()
                        bindingclass.textView4.text = bindingclass.textView4.text.toString() + price.toString()

                        bindingclass.textView6.text = bindingclass.textView6.text.toString() + key
                        bindingclass.textView7.text = bindingclass.textView7.text.toString() + accessibility.toString()

                    } else {
                        bindingclass.tv.text = "Error: ${response.code()}"
                    }

        }
                override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                    bindingclass.tv.text = "Error: ${t.message}"
                }
    })
}}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

    }

}