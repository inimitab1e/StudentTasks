package com.example.student_tasks.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.student_tasks.R
import com.example.student_tasks.data.model.ExpandableFacAndSpecModel

class FacultyAndSpecialityListAdapter(
    var context: Context,
    var facAndSpecModelList:MutableList<ExpandableFacAndSpecModel>
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var isFirstItemExpanded : Boolean = true
    private var actionLock = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ExpandableFacAndSpecModel.PARENT -> {FacAndSpecParentViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.expandable_parent_item, parent, false))}

            ExpandableFacAndSpecModel.CHILD -> { FacAndSpecChildViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.expandable_child_item, parent, false))  }

            else -> {FacAndSpecParentViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.expandable_parent_item, parent, false))}
        }
    }

    override fun getItemCount(): Int = facAndSpecModelList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val row = facAndSpecModelList[position]
        when(row.type){
            ExpandableFacAndSpecModel.PARENT -> {
                (holder as FacAndSpecParentViewHolder).countryName.text = row.facultyParent.facultyName
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
                holder.capitalImage.text = row.countryChild.capital
            }
        }

    }


    override fun getItemViewType(position: Int): Int = facAndSpecModelList[position].type

    private fun expandRow(position: Int){
        val row = facAndSpecModelList[position]
        var nextPosition = position
        when (row.type) {
            ExpandableFacAndSpecModel.PARENT -> {
                for(child in row.countryParent.states) {
                    facAndSpecModelList.add(++nextPosition, ExpandableFacAndSpecModel(ExpandableFacAndSpecModel.CHILD, child))
                }
                notifyDataSetChanged()
            }
            ExpandableCountryModel.CHILD -> {
                notifyDataSetChanged()
            }
        }
    }

    private fun collapseRow(position: Int){
        val row = countryStateModelList[position]
        var nextPosition = position + 1
        when (row.type) {
            ExpandableCountryModel.PARENT -> {
                outerloop@ while (true) {
                    //  println("Next Position during Collapse $nextPosition size is ${shelfModelList.size} and parent is ${shelfModelList[nextPosition].type}")

                    if (nextPosition == countryStateModelList.size || countryStateModelList[nextPosition].type == ExpandableCountryModel.PARENT) {
                        /* println("Inside break $nextPosition and size is ${closedShelfModelList.size}")
                         closedShelfModelList[closedShelfModelList.size-1].isExpanded = false
                         println("Modified closedShelfModelList ${closedShelfModelList.size}")*/
                        break@outerloop
                    }

                    countryStateModelList.removeAt(nextPosition)
                }

                notifyDataSetChanged()
            }


        }
    }

    class FacAndSpecParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var layout = itemView.country_item_parent_container
        internal var countryName : TextView = itemView.country_name
        internal var closeImage = itemView.close_arrow
        internal var upArrowImg = itemView.up_arrow

    }

    class FacAndSpecChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var layout = itemView.country_item_child_container
        internal var stateName : TextView = itemView.state_name
        internal var capitalImage = itemView.capital_name

    }
}