package com.example.todo20

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.editdialog.view.*


class todoadapter(private var items: MutableList<String>): RecyclerView.Adapter<todoadapter.ViewHolder>() {



    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val txt: TextView = view.findViewById(R.id.txt)
          val card:CardView =view.findViewById(R.id.card)

        val context = view.context



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.card, parent, false)


        return ViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.txt.text=item

      holder.card.setOnClickListener {
          dialog(holder.context,position,item)

      }




    }

    private fun dialog(context: Context, position: Int, item: String) {
        val build = LayoutInflater.from(context).inflate(R.layout.dialog,null)

        val makedialog =AlertDialog.Builder(context)

            .setView(build)


            .show()

        makedialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        build.delete.setOnClickListener {
            deleteitem(position)
            makedialog.dismiss()

        }
        build.edit.setOnClickListener {
            nextdialog(context,position,item)
            makedialog.dismiss()
        }
    }

    private fun nextdialog(context: Context, position: Int, item: String) {
        val secbuild = LayoutInflater.from(context).inflate(R.layout.editdialog,null)
           secbuild.editText.setText(item)
        val secdialog =AlertDialog.Builder(context)

            .setView(secbuild)

            .show()
        secdialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        secbuild.save.setOnClickListener {
            val item2 = secbuild.editText.text.toString()
            deleteitem(position)
            additem(position,item2)
            secdialog.dismiss()


        }
        secbuild.cancel.setOnClickListener {
            secdialog.dismiss()
        }


    }


    override fun getItemCount(): Int {
        return items.size
    }

    fun deleteitem(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()


    }
    fun additem(position: Int,item2: String){
        items.add(position,item2)
        notifyDataSetChanged()
    }
}