package com.example.click_task3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.click_task3.presentation.Screen
import com.example.click_task3.presentation.addtodo.AddTodoScreen
import com.example.click_task3.presentation.todolist.TodoListScreen
import com.example.click_task3.ui.ClickTask3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ClickTask3Theme() {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.TodoList.route
                    ) {
                        composable(Screen.TodoList.route) {
                            TodoListScreen(
                                onNavigateToAdd = {
                                    navController.navigate(Screen.AddTodo.route)
                                }
                            )
                        }

                        composable(Screen.AddTodo.route) {
                            AddTodoScreen(
                                onNavigateBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}