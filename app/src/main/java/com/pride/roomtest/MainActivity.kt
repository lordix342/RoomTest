package com.pride.roomtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.pride.roomtest.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var dataBase : DataBase
    private val rcAdapter = RcAdapter()
    private lateinit var viewM : ViewM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataBase = DataBase.getDatabase(this)
        binding.button.setOnClickListener {
            writeData()
        }
        viewM = ViewM(application)
        viewM.list.observe(this) {
           if(it!=null) {
               binding.rcview.adapter = rcAdapter
               binding.rcview.layoutManager = LinearLayoutManager(this)
               rcAdapter.setData(it)
           }
        }

    }

    private fun writeData() {
        val text = binding.editTextTextPersonName.text.toString()
        if (text.isNotEmpty()) {
            val name = Name(null,text)
            GlobalScope.launch(Dispatchers.IO) {
                dataBase.nameDao().insertToDB(name)
            }
        }
        viewM.readDb()
    }
}