package com.example.firstapp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.Models.Todo

import com.example.firstapp.R

class TodoAdapter(private var todos:MutableList<Todo>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView){
    val todoCheckBox:CheckBox=itemView.findViewById(R.id.todoCheckBox)
    val todoText:TextView=itemView.findViewById(R.id.todoText)
    val todoDelete:ImageView=itemView.findViewById(R.id.deleteIcon)
    }
   override fun onCreateViewHolder(allTodos:ViewGroup, viewType:Int):TodoViewHolder{
     val itemLayout=LayoutInflater.from(allTodos.context).inflate(R.layout.item_todo,allTodos,false)
     return TodoViewHolder(itemLayout)
}

    override fun onBindViewHolder(itemLayout: TodoViewHolder, position: Int) {
        val currentTodo=todos[position]
        itemLayout.todoText.text=currentTodo.text
        itemLayout.todoCheckBox.isChecked=currentTodo.isChecked
        itemLayout.todoCheckBox.setOnCheckedChangeListener { _, isChecked ->
            currentTodo.isChecked = isChecked
            itemLayout.todoText.paint.isStrikeThruText = isChecked
        }
        itemLayout.todoDelete.setOnClickListener{
            todos.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,todos.size)
        }

    }

    override fun getItemCount(): Int {
        return todos.size
    }

}



