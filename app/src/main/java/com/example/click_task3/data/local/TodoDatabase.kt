package com.example.click_task3.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.click_task3.data.local.entity.TodoItem

@Database(
    entities = [TodoItem::class],
    version = 1,
    exportSchema = false
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        const val DATABASE_NAME = "todo_database"
    }
}