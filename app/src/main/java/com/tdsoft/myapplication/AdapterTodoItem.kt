package com.tdsoft.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.listitem_todo.view.*

class AdapterTodoItem : RecyclerView.Adapter<AdapterTodoItem.MyViewHolder>() {
    private val list = ArrayList<ToDoItem>()
    inner class MyViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(
            parent.context
        ).inflate(
            R.layout.listitem_todo,
            parent,
            false
        )
    ) {
        fun bind(todoItem: ToDoItem) = with(itemView) {
            textTitle.text = todoItem.toTitle
            textDescription.text = todoItem.toDesc
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun addItem(todoItem: ToDoItem) {
        list.add(todoItem)
        notifyItemInserted(list.size)
    }
}