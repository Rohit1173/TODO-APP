package com.example.todo20

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class viewmodel :ViewModel(){
    var words: MutableList<String> = mutableListOf()
    var work:MutableLiveData<String> = MutableLiveData()




}