package com.example.student_tasks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_tasks.MainActivity
import com.example.student_tasks.R
import com.example.student_tasks.data.room.Users

class StudentsListAdapter() : RecyclerView.Adapter<StudentsListAdapter.UsersHolder>() {

    class UsersHolder(view: View): RecyclerView.ViewHolder(view) {
        val username = view.findViewById<TextView>(R.id.item_username)
        val email = view.findViewById<TextView>(R.id.item_email)
    }

    var usersList = mutableListOf<Users>()

    fun setUsers(user: List<Users>) {
        this.usersList = user.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_students_list, parent, false)
        return UsersHolder(view)
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val users = usersList[position]
        holder.username.text = users.userName
        holder.email.text = users.userEmail
    }
}