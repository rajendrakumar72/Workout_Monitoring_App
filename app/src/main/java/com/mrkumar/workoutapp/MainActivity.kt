package com.mrkumar.workoutapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mrkumar.workoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.flStart.setOnClickListener {
//            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
            val intent =Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }


    }


}