package com.example.networkdemo.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.networkdemo.R
import com.example.networkdemo.SecondActivity
import com.example.networkdemo.StudentInfo
import com.example.networkdemo.StudentsActivity
import com.example.networkdemo.model.Student2
import kotlinx.android.synthetic.main.student_item.view.*

class StudentAdapter(val StudentList:List<Student2>): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

        val stuNo: TextView =view.findViewById(R.id.studentNo)
        val stuName: TextView =view.findViewById(R.id.studentName)
        val studentImage: ImageView =view.findViewById(R.id.studentImage)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentAdapter.ViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        var view = inflater.inflate(R.layout.student_item, parent, false)
        val viewHolder=ViewHolder(view)
        viewHolder.itemView.setOnClickListener{
            val position=viewHolder.adapterPosition
            val student:Student2=StudentList[position]
            Toast.makeText(parent.context,student.stuName, Toast.LENGTH_LONG).show()
        }
        viewHolder.studentImage.setOnClickListener{
            val position=viewHolder.adapterPosition
            val student:Student2=StudentList[position]
            Toast.makeText(parent.context,student.toString(), Toast.LENGTH_LONG).show()
        }
        viewHolder.stuNo.setOnClickListener{
            val position=viewHolder.adapterPosition
            val student:Student2=StudentList[position]
            Toast.makeText(parent.context,student.toString(), Toast.LENGTH_LONG).show()
        }

        viewHolder.stuName.setOnClickListener{
            val position=viewHolder.adapterPosition
            val student:Student2=StudentList[position]
            Toast.makeText(parent.context,student.toString(), Toast.LENGTH_LONG).show()
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return StudentList.size
    }

    override fun onBindViewHolder(holder: StudentAdapter.ViewHolder, position: Int) {
        var student = StudentList[position]
        holder.studentImage.setImageResource(student.photoPath)
        holder.stuName.text=student.stuName
        holder.stuNo.text=student.stuNo
    }
}