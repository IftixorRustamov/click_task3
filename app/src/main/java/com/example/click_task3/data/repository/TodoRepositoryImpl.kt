package com.example.click_task3.data.repository

import com.example.click_task3.data.local.TodoDao
import com.example.click_task3.data.local.entity.TodoItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override fun getAllTodos(): Flow<List<TodoItem>> {
        return todoDao.getAllTodos()
    }

    override suspend fun getTodoById(id: Long): TodoItem? {
        return todoDao.getTodoById(id)
    }

    override suspend fun insert(todo: TodoItem) {
        todoDao.insert(todo)
    }

    override suspend fun update(todo: TodoItem) {
        todoDao.update(todo)
    }

    override suspend fun delete(todo: TodoItem) {
        todoDao.delete(todo)
    }

    override suspend fun toggleComplete(id: Long) {
        val todo = todoDao.getTodoById(id)
        todo?.let {
            todoDao.update(it.copy(isCompleted = !it.isCompleted))
        }
    }
}