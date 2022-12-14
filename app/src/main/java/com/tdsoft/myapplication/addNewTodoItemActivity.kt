package com.tdsoft.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.Key.VISIBILITY
import kotlinx.android.synthetic.main.activity_add_new_todo_item.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addNewTodoItemActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_CODE_ADD_TODO_ITEM = 1000
        const val EXTRA_TODO_ITEM = "todo_item"
        fun startActivity(activity: AppCompatActivity) {
            val intent = Intent(activity, addNewTodoItemActivity::class.java)
            activity.startActivityForResult(intent, REQUEST_CODE_ADD_TODO_ITEM)
        }
    }

    private var todoItem = ToDoItem(-1, "", "")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_todo_item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_add_todo_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_done-> {
                if(etTitle.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter a Title", Toast.LENGTH_LONG).show()
                    return true
                }

                if(etDesc.text.isNullOrEmpty()) {
                    Toast.makeText(this, "Please enter a Description", Toast.LENGTH_LONG).show()
                    return true
                }

                todoItem.toTitle = etTitle.text.toString()
                todoItem.toDesc = etDesc.text.toString()
                addTodoItemToServer()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun addTodoItemToServer() {
        progressBar.visibility = View.VISIBLE
        MyApp.getInstance().getApiServices().addTodoItem().enqueue(object : Callback<ToDoItem> {
            override fun onResponse(call: Call<ToDoItem>, response: Response<ToDoItem>) {
                progressBar.visibility = View.GONE
                val data = Intent()
                data.putExtra(EXTRA_TODO_ITEM, todoItem)
                setResult(Activity.RESULT_OK, data)
                finish()
            }

            override fun onFailure(call: Call<ToDoItem>, t: Throwable) {
                progressBar.visibility = View.GONE
                Toast.makeText(
                    this@addNewTodoItemActivity,
                    "Failed to post, Bitch!!",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}