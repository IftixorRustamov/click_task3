package com.example.click_task3.presentation

sealed class Screen(val route: String) {
    data object TodoList : Screen("todo_list")
    data object AddTodo : Screen("add_todo")
}
