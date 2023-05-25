package com.painrate.modules.patients.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.painrate.R

class CustomAdapter(private val patientList : ArrayList<Datalist>) : RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    private lateinit var mListner: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun onItemClickListener(listener: onItemClickListener){
        mListner = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.patientrow, parent, false)
        return MyViewHolder(itemView, mListner)
    }

    override fun getItemCount(): Int {

        return patientList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = patientList[position]
        holder.name.text = currentItem.name
        holder.phone.text = currentItem.phone
        holder.notes.text = currentItem.notes
        holder.location.text = currentItem.location
        holder.date.text = currentItem.dob

    }

    class MyViewHolder(itemView : View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){

//        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_Image)
        val id : TextView = itemView.findViewById(R.id.idText)
        val name : TextView = itemView.findViewById(R.id.nameText)
        val phone : TextView = itemView.findViewById(R.id.phoneText)
        val notes : TextView = itemView.findViewById(R.id.notesText)
        val location : TextView = itemView.findViewById(R.id.locText)
        val date : TextView = itemView.findViewById(R.id.dateText)

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }



    }

}