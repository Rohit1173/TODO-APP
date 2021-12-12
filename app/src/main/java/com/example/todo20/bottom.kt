package com.example.todo20

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.todo20.data.word
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom.*

class bottom : BottomSheetDialogFragment() {
    lateinit var vm:viewmodel
    override  fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_bottom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      vm=ViewModelProvider(this).get(viewmodel::class.java)

        mainbtn.setOnClickListener {
          vm.additem(word(0,input.text.toString().trim()))
            input.text?.clear()
            dismiss()
        }
    }


}