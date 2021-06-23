package com.example.todo20

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val sharedViewModel: viewmodel by viewModels()

           sharedViewModel.work.observe(this, Observer {

               sharedViewModel.words.add(it.toString())
               recycler.adapter = todoadapter(sharedViewModel.words)

           })
        recycler.adapter = todoadapter(sharedViewModel.words)


        recycler.setHasFixedSize(true)

        val bottomsheet=bottom()


        add.setOnClickListener{
            recycler.adapter = todoadapter(sharedViewModel.words)



            bottomsheet.show(supportFragmentManager,"ro")

        }



    }

    fun View.hidekeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


}