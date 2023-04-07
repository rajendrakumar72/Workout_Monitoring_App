package com.mrkumar.workoutapp

import android.app.Application
import com.mrkumar.workoutapp.db.HistoryDatabase

class WorkoutApp:Application() {

    val db by lazy {
        HistoryDatabase.getInstance(this)
    }
}