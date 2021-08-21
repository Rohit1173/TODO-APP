package com.example.todo20

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.example.todo20.data.word


class dialog(myid:Int,myword:String) : DialogFragment() {
    val meid:Int =myid
    val me : String = myword
    lateinit var vm:viewmodel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v= inflater.inflate(R.layout.fragment_dialog, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        vm= ViewModelProvider(this).get(viewmodel::class.java)
        val heading:TextView = v.findViewById(R.id.heading)
        val editword:Button =v.findViewById(R.id.edit)
        val deleteword:Button =v.findViewById(R.id.delete)
        editword.setOnClickListener {
            val fm : FragmentManager = (context as AppCompatActivity).supportFragmentManager
            val editdialog =edit(meid,me)
            editdialog.show(fm,"edit")
            dismiss()
        }
        deleteword.setOnClickListener {
           vm.deleteword(word(meid,me))
            dismiss()
        }
        heading.text=me
        return v
    }


}