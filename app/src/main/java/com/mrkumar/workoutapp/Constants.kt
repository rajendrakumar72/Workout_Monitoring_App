package com.mrkumar.workoutapp

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList =ArrayList<ExerciseModel>()

        val jumpingJack=ExerciseModel(
            1,
            "Jumping Jack",
            R.drawable.ic_jumping_jacks)
        exerciseList.add(jumpingJack)

        val abdominalCrunch=ExerciseModel(
            2,
            "Abdominal Crunch",
            R.drawable.ic_abdominal_crunch)
        exerciseList.add(abdominalCrunch)

        val highKneesRunningPlace=ExerciseModel(
            3,
            "High Knees Running In Place",
            R.drawable.ic_high_knees_running_in_place)
        exerciseList.add(highKneesRunningPlace)


        val lunge=ExerciseModel(
            4,
            "Lunge",
            R.drawable.ic_lunge)
        exerciseList.add(lunge)

        val plank=ExerciseModel(
            5,
            "Plank",
            R.drawable.ic_plank)
        exerciseList.add(plank)

        val pushUp=ExerciseModel(
            6,
            "Push Up",
            R.drawable.ic_push_up)
        exerciseList.add(pushUp)

        val pushUpAndRotation=ExerciseModel(
            7,
            "PushUp And Rotation",
            R.drawable.ic_plank)
        exerciseList.add(pushUpAndRotation)

        val sidePlank=ExerciseModel(
            8,
            "Side Plank",
            R.drawable.ic_side_plank)
        exerciseList.add(sidePlank)

        val squat=ExerciseModel(
            9,
            "Squat",
            R.drawable.ic_squat)
        exerciseList.add(squat)

        val stepUpOntoChair=ExerciseModel(
            10,
            "StepUp On To Chair",
            R.drawable.ic_step_up_onto_chair)
        exerciseList.add(stepUpOntoChair)

        val tricepsDipOnChair=ExerciseModel(
            11,
            "Triceps Dip On Chair",
            R.drawable.ic_triceps_dip_on_chair)
        exerciseList.add(tricepsDipOnChair)

        val wallSit=ExerciseModel(
            12,
            "Wall Sit",
            R.drawable.ic_wall_sit)
        exerciseList.add(wallSit)

        return exerciseList
    }
}