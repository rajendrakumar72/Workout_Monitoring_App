package com.mrkumar.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mrkumar.workoutapp.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    var binding:ActivityExerciseBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        val view= binding!!.root
        setContentView(view)

        setSupportActionBar(binding!!.toolbarExercise)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding!!.toolbarExercise.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}