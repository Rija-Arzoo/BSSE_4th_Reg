package com.example.firstapp
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.Models.Todo
import com.example.firstapp.adapter.TodoAdapter


class RecycleView:AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val addButton = findViewById<Button>(R.id.addButton)
        val todoInput = findViewById<EditText>(R.id.todoText)

        val todoList = mutableListOf<Todo>()
        todoAdapter = TodoAdapter(todoList)

        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val todoText = todoInput.text.toString()
            if (todoText.isNotEmpty()) {
                todoList.add(Todo(todoText))
                todoAdapter.notifyItemInserted(todoList.size - 1)
                todoInput.text.clear()
            }
        }

    }
}