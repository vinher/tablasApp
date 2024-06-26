package com.kev.tablasapp

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.kev.tablasapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialogFragment       :Dialog
    private lateinit var dialogSuccess        :Dialog
    private lateinit var dialogError          :Dialog
    private lateinit var dialogCongratulation :Dialog
    var sp:SoundPool?=null
    var successSound = 0
    var errorSound = 0

    private var contador:Int = 1
    private var result:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        sp = SoundPool(1, AudioManager.STREAM_MUSIC,1)
        successSound = sp?.load(this,R.raw.success,1)!!
        errorSound == sp?.load(this,R.raw.error,1)!!
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        createTables()
    }


    fun soundSuccess(){
        val mp = MediaPlayer.create(this,R.raw.success)
        mp.start()
    }
    fun soundError(){
        val mp = MediaPlayer.create(this,R.raw.error)
        mp.start()
    }

    private fun createTables() {
        dialogFragment = initDialog(this,R.layout.fragment_custom_dialog,0.9,0.4,false,true)
        dialogSuccess = initDialog(this,R.layout.dialog_corrects,0.9,0.4,false,true)
        binding.inputTable.setOnClickListener {
            if (binding.inputTable.text.isNullOrEmpty()){
                showMessage("Ingresa un numero")
                soundError()
            }else{
                if(binding.inputTable.text.toString().toInt() > 10){
                    showMessage("Aprende las primeras 10 tablas de multiplicar")
                    soundError()
                }else if(binding.inputTable.text.toString().toInt() == 0){
                    showMessage("Ingresa un numero mayor a 0 ")
                    soundError()
                }else{
                    var data = ArrayList<String>()
                    val number = Integer.parseInt(binding.inputTable.text.toString())
                    for(i in 1..10){
                        Log.e("Mensaje","El numero multiplicado es : ${i * number}")
                        data.add("${binding.inputTable.text.toString()} X ${i} = ${i * number}")

                    }
                    val customadapter = RecyclerAdapter(data)
                    val recyclerView:RecyclerView = binding.rvItems
                    val layoutManager = LinearLayoutManager(this)
                    recyclerView.setLayoutManager(layoutManager)
                    recyclerView.adapter = customadapter
                    binding.inputTable.setText("");
                    exercises(data)
                }
            }

        }
    }
    private fun exercises(data:ArrayList<String>) {
        binding.fab.visibility = View.VISIBLE
        val data_gral = data.random()
        val exercise = data_gral.substringBefore("=")
        result = data_gral.substringAfter("=").trim()

        if(data.size > 0){
            binding.fab.visibility = View.VISIBLE
            binding.fab.setOnClickListener {
                //Logica De Juego
                dialogFragment.show()
                dialogFragment.findViewById<TextView>(R.id.init_exercise).setText(contador.toString())
                dialogFragment.findViewById<TextView>(R.id.counter).setText("5")
                dialogFragment.findViewById<TextView>(R.id.res_exercise).setText(exercise)
            }

            dialogFragment.findViewById<Button>(R.id.aceptar).setOnClickListener {
                var respuesta_user = dialogFragment.findViewById<TextInputEditText>(R.id.validRes).text.toString()
                validResponse(respuesta_user,result,data)
            }
        }
    }

    private fun validResponse(respuestaUser: String, result: String, data: ArrayList<String>) {
        if(respuestaUser.trim() == result.toString()){
            dialogFragment.dismiss()
            soundSuccess()

            clicloApp(data)
        }else{
            showMessage("Algo salio mal")
            soundError()
        }
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root,message, Snackbar.LENGTH_LONG).show()
    }

    private fun clicloApp(data: ArrayList<String>) {
        dialogFragment.findViewById<TextInputEditText>(R.id.validRes).setText("")
        val data_gral = data.random()
        val exercise = data_gral.substringBefore("=")
        result = data_gral.substringAfter("=").trim()
        dialogFragment.show()
        contador += 1
        if(contador == 6){
            contador = 1
            dialogFragment.dismiss()
            greatPhrases()
        }else{
            dialogFragment.findViewById<TextView>(R.id.res_exercise).setText(exercise)
            dialogFragment.findViewById<TextView>(R.id.init_exercise).setText(contador.toString())
            dialogFragment.findViewById<TextView>(R.id.counter).setText("5")
        }
    }
    private fun greatPhrases(){
        dialogSuccess = initDialog(this,R.layout.dialog_congratulation,0.9,0.4,true,true)
        var phrases = arrayOf(
            "Bien Echo",
            "Felicidades",
            "Sigue Así",
            "Estas Aprendiendo Mucho",
            "Eres un genio",
            "Dominas las matematicas",
            "Eres un genio",
            "Perfecto lo hiciste genial",
            "Buena Respuesta",
            "Excelente trabajo"
        )
        dialogSuccess.show()
        dialogSuccess.findViewById<TextView>(R.id.show_text).setText(phrases.random().toString())
        //Thread.sleep(3000)
       // dialogSuccess.dismiss()
        }
    }




    fun initDialog(
        activity: Activity,
        layout: Int,
        ancho: Double,
        alto: Double,
        cancelable: Boolean,
        transparente: Boolean
    ): Dialog {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(cancelable)
        dialog.setContentView(layout)

        // Obtener las métricas de la pantalla
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = (displayMetrics.widthPixels * ancho).toInt()
        val height = (displayMetrics.heightPixels * alto).toInt()

        dialog.window?.setLayout(width, height)
        if (transparente)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

