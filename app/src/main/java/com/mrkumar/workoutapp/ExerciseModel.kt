package com.mrkumar.workoutapp

data class ExerciseModel(
    private var id:Int,
    private var name:String,
    private var imageId:Int,
    private var isCompleted:Boolean=false,
    private var isSelected:Boolean=false
)