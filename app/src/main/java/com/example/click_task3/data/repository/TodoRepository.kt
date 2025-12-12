package com.example.click_task3.data.repository

import com.example.click_task3.data.local.entity.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun getTodoById(id: Long): TodoItem?
    suspend fun insert(todo: TodoItem)
    suspend fun update(todo: TodoItem)
    suspend fun delete(todo: TodoItem)
    suspend fun toggleComplete(id: Long)
}