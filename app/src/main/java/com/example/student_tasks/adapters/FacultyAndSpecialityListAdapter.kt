package com.example.student_tasks.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.student_tasks.R
import com.example.student_tasks.data.model.ExpandableFacAndSpecModel

class FacultyAndSpecialityListAdapter(
    var facAndSpecModelList:MutableList<ExpandableFacAndSpecModel>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isFirstItemExpanded : Boolean = true
    private var actionLock = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ExpandableFacAndSpecModel.PARENT -> {FacAndSpecParentViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.faculty_and_speciality_list_parent_item, parent, false))}

            ExpandableFacAndSpecModel.CHILD -> { FacAndSpecChildViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.faculty_and_speciality_list_child_item, parent, false))  }

            else -> {FacAndSpecParentViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.faculty_and_speciality_list_parent_item, parent, false))}
        }
    }

    override fun getItemCount(): Int = facAndSpecModelList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = facAndSpecModelList[position]
        when(row.type){
            ExpandableFacAndSpecModel.PARENT -> {
                (holder as FacAndSpecParentViewHolder).facultyName.text = row.facultyParent.facultyName
                holder.closeImage.setOnClickListener {
                    if (row.isExpanded) {
                        row.isExpanded = false
                        collapseRow(position)
                        holder.layout.setBackgroundColor(Color.WHITE)
                    } else {
                        holder.layout.setBackgroundColor(Color.GRAY)
                        row.isExpanded = true
                        holder.upArrowImg.visibility = View.VISIBLE
                        holder.closeImage.visibility = View.GONE
                        expandRow(position)
                    }
                }
                holder.upArrowImg.setOnClickListener{
                    if(row.isExpanded){
                        row.isExpanded = false
                        collapseRow(position)
                        holder.layout.setBackgroundColor(Color.WHITE)
                        holder.upArrowImg.visibility = View.GONE
                        holder.closeImage.visibility = View.VISIBLE

                    }
                }
            }


            ExpandableFacAndSpecModel.CHILD -> {
                (holder as FacAndSpecChildViewHolder).specialityName.text = row.facultyChild.specialityName
            }
        }

    }

    override fun getItemViewType(position: Int): Int = facAndSpecModelList[position].type

    private fun expandRow(position: Int){
        val row = facAndSpecModelList[position]
        var nextPosition = position
        when (row.type) {
            ExpandableFacAndSpecModel.PARENT -> {
                for(child in row.facultyParent.specialityList) {
                    facAndSpecModelList.add(++nextPosition, ExpandableFacAndSpecModel(ExpandableFacAndSpecModel.CHILD, child))
                }
                notifyDataSetChanged()
            }
            ExpandableFacAndSpecModel.CHILD -> {
                notifyDataSetChanged()
            }
        }
    }

    private fun collapseRow(position: Int){
        val row = facAndSpecModelList[position]
        var nextPosition = position + 1
        when (row.type) {
            ExpandableFacAndSpecModel.PARENT -> {
                outerloop@ while (true) {
                    //  println("Next Position during Collapse $nextPosition size is ${shelfModelList.size} and parent is ${shelfModelList[nextPosition].type}")
                    if (nextPosition == facAndSpecModelList.size || facAndSpecModelList[nextPosition].type == ExpandableFacAndSpecModel.PARENT) {
                        /* println("Inside break $nextPosition and size is ${closedShelfModelList.size}")
                         closedShelfModelList[closedShelfModelList.size-1].isExpanded = false
                         println("Modified closedShelfModelList ${closedShelfModelList.size}")*/
                        break@outerloop
                    }
                    facAndSpecModelList.removeAt(nextPosition)
                }
                notifyDataSetChanged()
            }
        }
    }

    class FacAndSpecParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var layout = itemView.findViewById<ConstraintLayout>(R.id.fac_and_spec_parent_container)
        internal var facultyName = itemView.findViewById<TextView>(R.id.faculty_name)
        internal var closeImage = itemView.findViewById<ImageView>(R.id.close_arrow)
        internal var upArrowImg = itemView.findViewById<ImageView>(R.id.up_arrow)

    }

    class FacAndSpecChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var layout = itemView.findViewById<ConstraintLayout>(R.id.fac_and_spec_child_container)
        internal var specialityName = itemView.findViewById<TextView>(R.id.speciality_name)
    }
}