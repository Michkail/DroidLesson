package com.tdsoft.myapplication

import retrofit2.Call
import retrofit2.http.POST

interface APIServices {
    @POST("addTodoItems")
    fun addTodoItem() : Call<ToDoItem>
}