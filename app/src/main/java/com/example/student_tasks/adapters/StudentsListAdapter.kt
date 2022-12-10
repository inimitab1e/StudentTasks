package com.example.student_tasks.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_tasks.R
import com.example.student_tasks.data.room.Users

class StudentsListAdapter() : RecyclerView.Adapter<StudentsListAdapter.UsersHolder>() {

    class UsersHolder(view: View): RecyclerView.ViewHolder(view) {
        val username = view.findViewById<TextView>(R.id.item_username)
        val email = view.findViewById<TextView>(R.id.item_email)
    }

    private var usersList = mutableListOf<Users>()

    fun setUsers(user: List<Users>) {
        this.usersList = user.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UsersHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_students_list, viewGroup, false)
        return UsersHolder(view)
    }

    override fun getItemCount(): Int = usersList.size

    override fun onBindViewHolder(holder: UsersHolder, position: Int) {
        val users = usersList[position]
        holder.username.text = users.userName
        holder.email.text = users.userEmail
    }
}