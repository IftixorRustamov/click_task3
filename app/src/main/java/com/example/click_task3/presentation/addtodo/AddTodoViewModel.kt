package com.example.click_task3.presentation.addtodo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.click_task3.data.local.entity.TodoItem
import com.example.click_task3.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _title = MutableStateFlow("")
    val title: StateFlow<String> = _title.asStateFlow()

    private val _isSaving = MutableStateFlow(false)
    val isSaving: StateFlow<Boolean> = _isSaving.asStateFlow()

    fun onTitleChange(newTitle: String) {
        _title.value = newTitle
    }

    fun addTodo(onSuccess: () -> Unit) {
        if (_title.value.isBlank() || _isSaving.value) return

        viewModelScope.launch {
            _isSaving.value = true
            try {
                repository.insert(
                    TodoItem(title = _title.value.trim())
                )
                onSuccess()
            } finally {
                _isSaving.value = false
            }
        }
    }

    val isValid: Boolean
        get() = _title.value.isNotBlank()
}