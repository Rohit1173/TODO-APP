package com.example.todo20

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.todo20.data.word
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
   lateinit var vm:viewmodel
   lateinit var myadapter:todoadapter
   lateinit var recycler: RecyclerView
   var list:MutableList<word>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler=findViewById(R.id.recycler)
        supportActionBar?.hide()
           vm=ViewModelProvider(this).get(viewmodel::class.java)
           vm.allwords.observe(this,  {
                 list=it
                myadapter= todoadapter(list!!)
               recycler.adapter = myadapter

           })



        recycler.setHasFixedSize(true)

        val bottomsheet=bottom()


        add.setOnClickListener{
            bottomsheet.show(supportFragmentManager,"ro")

        }
        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val sp =viewHolder.adapterPosition
                val tp = target.adapterPosition
                val sp_w:String=list!![sp].work
                val tp_w:String=list!![tp].work
                Collections.swap(list,sp,tp)
                myadapter.notifyItemMoved(sp,tp)
                return true


            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val dp = viewHolder.adapterPosition
                val del:word=list!![dp]
                when(direction) {

                    ItemTouchHelper.LEFT->{
                        val fm : FragmentManager = this@MainActivity.supportFragmentManager
                        val editdialog =edit(del.id,del.work)
                        editdialog.show(fm,"edit")
                    }
                    ItemTouchHelper.RIGHT-> {

                        vm.deleteword(list!![dp])
                        myadapter.notifyDataSetChanged()
                        Snackbar.make(recycler,"Deleted",Snackbar.LENGTH_LONG).setAction("Undo"){
                            vm.additem(del)
                        }.show()
                    }
                }

            }

        })
        touchHelper.attachToRecyclerView(recycler)



    }

    fun View.hidekeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }


}