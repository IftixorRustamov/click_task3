# 📱 To-Do Application

Modern Android To-Do app built with **MVVM**, **Room Database**, **StateFlow**, and **Jetpack Compose**.

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-blue.svg)](https://kotlinlang.org)
[![MinSDK](https://img.shields.io/badge/MinSDK-24-green.svg)](https://developer.android.com)

---

## ✨ Features

- ✅ Add, complete, and delete tasks
- ✅ Swipe to delete with animation
- ✅ Persistent storage (Room Database)
- ✅ Real-time updates (StateFlow)
- ✅ Material 3 Design with dark theme

---

## 🛠 Tech Stack

- **Kotlin** - Programming language
- **Jetpack Compose** - Declarative UI
- **Room Database** - Local persistence
- **Hilt** - Dependency injection
- **Coroutines & StateFlow** - Async & reactive state
- **MVVM Architecture** - Clean separation of concerns

---

## 🏗️ Architecture

```
Presentation (UI + ViewModels)
        ↓ StateFlow
Repository (Interface)
        ↓
Data Layer (Room DB + DAO)
```

### Project Structure

```
app/src/main/java/com/example/click_task3/
├── TodoApplication.kt
├── data/
│   ├── local/
│   │   ├── TodoDatabase.kt
│   │   ├── TodoDao.kt
│   │   └── entity/TodoItem.kt
│   └── repository/
│       ├── TodoRepository.kt
│       └── TodoRepositoryImpl.kt
├── di/
│   └── AppModule.kt
├── presentation/
│   ├── MainActivity.kt
│   ├── Screen.kt
│   ├── todolist/
│   │   ├── TodoListScreen.kt
│   │   └── TodoListViewModel.kt
│   └── addtodo/
│       ├── AddTodoScreen.kt
│       └── AddTodoViewModel.kt
└── ui/theme/Theme.kt
```

---

## 🚀 Setup

### Prerequisites
- Android Studio Hedgehog or newer
- JDK 17+
- Min SDK 24

### Installation

1. **Clone repository**
   ```bash
   git clone https://github.com/yourusername/todo-app.git
   ```

2. **Open in Android Studio**

3. **Sync Gradle and Run**

---

## 📱 Usage

- **Add Task**: Tap the `+` button, enter title, and save
- **Complete Task**: Tap the checkbox
- **Delete Task**: Swipe left on task card

---

## 🔑 Key Components

### Entity
```kotlin
@Entity(tableName = "todo_items")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val isCompleted: Boolean = false
)
```

### ViewModel
```kotlin
@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {
    
    val todos: StateFlow<List<TodoItem>> = repository
        .getAllTodos()
        .stateIn(viewModelScope, WhileSubscribed(5000), emptyList())
    
    fun toggleComplete(id: Long) {
        viewModelScope.launch { repository.toggleComplete(id) }
    }
}
```

### Repository
```kotlin
interface TodoRepository {
    fun getAllTodos(): Flow<List<TodoItem>>
    suspend fun insert(todo: TodoItem)
    suspend fun toggleComplete(id: Long)
    suspend fun delete(todo: TodoItem)
}
```

---

## 📦 Dependencies

```kotlin
// Core
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
implementation("androidx.navigation:navigation-compose:2.7.6")

// Room
implementation("androidx.room:room-ktx:2.6.1")
ksp("androidx.room:room-compiler:2.6.1")

// Hilt
implementation("com.google.dagger:hilt-android:2.50")
ksp("com.google.dagger:hilt-android-compiler:2.50")
implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
```

---

## 🧪 Testing

```kotlin
@Test
fun `adding todo updates state`() = runTest {
    val repository = FakeTodoRepository()
    val viewModel = AddTodoViewModel(repository)
    
    viewModel.onTitleChange("Test Task")
    viewModel.addTodo {}
    
    val todos = repository.getAllTodos().first()
    assertEquals(1, todos.size)
}
```

---

## 📄 License

MIT License - see [LICENSE](LICENSE) file

---

## 📞 Contact

**Developer**: [Your Name]  
**Email**: your.email@example.com  
**GitHub**: [@yourusername](https://github.com/yourusername)

---

<div align="center">
  <p>Made with ❤️ using Kotlin & Jetpack Compose</p>
</div>
