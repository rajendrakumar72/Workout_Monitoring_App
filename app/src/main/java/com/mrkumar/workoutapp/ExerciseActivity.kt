package com.mrkumar.workoutapp

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mrkumar.workoutapp.databinding.ActivityExerciseBinding
import com.mrkumar.workoutapp.databinding.DialogCustomConfirmationBackBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var binding:ActivityExerciseBinding?=null

    private var resetTimerDuration:Long = 10
    private var resetTimer:CountDownTimer?=null
    private var restProgress =0

    private var exerciseTimerDuration:Long = 30
    private var exerciseTimer:CountDownTimer?=null
    private var exerciseProgress =0

    private var exerciseList:ArrayList<ExerciseModel>?=null
    private var currentExercisePosition = -1

    private var tts:TextToSpeech?=null

    private var player:MediaPlayer?=null

    private var exerciseAdapter:ExerciseStatusAdapter?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExerciseBinding.inflate(layoutInflater)
        val view= binding!!.root
        setContentView(view)

        setSupportActionBar(binding!!.toolbarExercise)
        tts= TextToSpeech(this,this)

        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding!!.toolbarExercise.setNavigationOnClickListener {
            customDialogForBackButton()
        }
        exerciseList = Constants.defaultExerciseList()
        setupRestView()
        setupExerciseRecyclerview()
    }

    private fun customDialogForBackButton() {
        val customDialog=Dialog(this)
        val dialogBinding=DialogCustomConfirmationBackBinding.inflate(layoutInflater)
        customDialog.setContentView(dialogBinding.root)
        customDialog.setCanceledOnTouchOutside(false)

        dialogBinding.btnYes.setOnClickListener {
            this@ExerciseActivity.finish()
            customDialog.dismiss()
        }
        dialogBinding.btnNo.setOnClickListener {
            customDialog.dismiss()
        }

        customDialog.show()
    }

    override fun onBackPressed() {
        customDialogForBackButton()
//        super.onBackPressed()
    }
    private fun setupRestView(){
        try{
            val soundUri=Uri.parse("android.resource://com.mrkumar.workoutapp"+R.raw.press_start)

            player=MediaPlayer.create(applicationContext,soundUri)
            player?.isLooping=false
            player?.start()

        }catch (e:Exception){
            e.printStackTrace()
        }
        binding?.flRestView?.visibility=View.VISIBLE
        binding?.tvTitle?.visibility=View.VISIBLE
        binding?.flExerciseView?.visibility=View.INVISIBLE
        binding?.tvExerciseName?.visibility=View.INVISIBLE
        binding?.ivImage?.visibility=View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE

        if (resetTimer!=null){
            resetTimer?.cancel()
            restProgress=0
        }
        binding?.tvUpcomingExerciseName?.text = exerciseList!![currentExercisePosition + 1].getName()
        setRestProgressBar()
    }

    private fun setupExerciseRecyclerview(){
        binding?.rvExerciseStatus?.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        exerciseAdapter=ExerciseStatusAdapter( exerciseList!!)

        binding?.rvExerciseStatus?.adapter=exerciseAdapter
    }
    private fun setupExerciseView(){
        binding?.flRestView?.visibility=View.INVISIBLE
        binding?.tvTitle?.visibility=View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.upcomingLabel?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility=View.VISIBLE
        binding?.tvExerciseName?.visibility=View.VISIBLE
        binding?.ivImage?.visibility=View.VISIBLE


        if (exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }
        speakOut(exerciseList!![currentExercisePosition].getName())
        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text =exerciseList!![currentExercisePosition].getName()
        setExerciseProgressBar()
    }

    private fun setRestProgressBar(){
        binding?.progressBar?.progress=restProgress

        resetTimer =object :CountDownTimer(resetTimerDuration*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress=10-restProgress
                binding?.tvTimer?.text=(10-restProgress).toString()

            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.progressBarExercise?.progress=exerciseProgress

        exerciseTimer =object :CountDownTimer(exerciseTimerDuration*1000,1000){
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                binding?.progressBarExercise?.progress=30-exerciseProgress
                binding?.tvTimerExercise?.text=(30-exerciseProgress).toString()

            }

            override fun onFinish() {

                if(currentExercisePosition<exerciseList?.size!!-1){
                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else {
                   finish()
                    val intent=Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }

        }.start()
    }
    override fun onDestroy() {
        super.onDestroy()

        if (resetTimer!=null){
            resetTimer?.cancel()
            restProgress=0
        }
        if (exerciseTimer!=null){
            exerciseTimer?.cancel()
            exerciseProgress=0
        }

        if (tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if (player!=null){
            player!!.stop()
        }
        binding=null
    }

    override fun onInit(status: Int) {
        if (status==TextToSpeech.SUCCESS){
            val result=tts?.setLanguage(Locale.US)

            if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("tts", "The Language Specified is Not Supported: ")
            }
        }else{
            Log.e("tts", "Initialization Failed: ")
        }
    }

    private fun speakOut(text:String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

}