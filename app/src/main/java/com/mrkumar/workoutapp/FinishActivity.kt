package com.mrkumar.workoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.mrkumar.workoutapp.databinding.ActivityFinishBinding
import com.mrkumar.workoutapp.db.HistoryDao
import com.mrkumar.workoutapp.db.HistoryEntity
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {
    private  var binding: ActivityFinishBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.btnFinish?.setOnClickListener {
            finish()
        }

        val dao=(application as WorkoutApp).db.historyDao()
        addDateToDB(dao)
    }

    private fun addDateToDB(historyDao: HistoryDao){
        val c =Calendar.getInstance()
        val dateTime=c.time

        val sdf=SimpleDateFormat("dd MMM yyyy HH:mm:ss",Locale.getDefault())
        val date=sdf.format(dateTime)
        Log.d("Formatted Date : ", "" + date)
        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }
    }

}