package com.example.todo20

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
   lateinit var vm:viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
           vm=ViewModelProvider(this).get(viewmodel::class.java)
           vm.allwords.observe(this,  {


               recycler.adapter = todoadapter(it)

           })



        recycler.setHasFixedSize(true)

        val bottomsheet=bottom()


        add.setOnClickListener{
            bottomsheet.show(supportFragmentManager,"ro")

        }



    }

    fun View.hidekeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


}