package com.example.student_tasks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.student_tasks.MainActivity
import com.example.student_tasks.R
import com.example.student_tasks.data.room.Users

class StudentsListAdapter() : RecyclerView.Adapter<StudentsListAdapter.StudentHolder>() {

    class StudentHolder(view: View): RecyclerView.ViewHolder(view) {
        val username =
    }

    var studentList = mutableListOf<Users>()

    fun setStudents(student: List<Users>) {
        this.studentList = student.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_students_list, parent, false)
        return StudentHolder(view)
    }

    override fun getItemCount(): Int = studentList.size

    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        val students = studentList[position]

    }
}