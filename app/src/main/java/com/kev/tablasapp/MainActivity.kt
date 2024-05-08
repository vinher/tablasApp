package com.kev.tablasapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kev.tablasapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }




        createTables()
    }

    private fun createTables() {



        binding.inputTable.setOnClickListener {
            if (binding.inputTable.text.isNullOrEmpty()){
                Toast.makeText(this,"Ingresa un número", Toast.LENGTH_LONG).show()
            }else{
                if(binding.inputTable.text.toString().toInt() > 10){
                    Toast.makeText(this,"Aprende las 10 primeras tablas", Toast.LENGTH_LONG).show()

                }else{
                    var data = ArrayList<String>()
                    val number = Integer.parseInt(binding.inputTable.text.toString())
                    for(i in 1..10){
                        Log.e("Mensaje","El numero multiplicado es : ${i * number}")
                        data.add("${binding.inputTable.text.toString()} X ${i} = ${i * number}")

                    }
                    Log.e("Data",data.toString())
                    val customadapter = RecyclerAdapter(data)
                    val recyclerView:RecyclerView = binding.rvItems
                    val layoutManager = LinearLayoutManager(this)
                    recyclerView.setLayoutManager(layoutManager)
                    recyclerView.adapter = customadapter
                    binding.inputTable.setText("");
                }
            }

        }
    }
}