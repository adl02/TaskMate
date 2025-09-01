package com.howtokaise.taskmate.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.howtokaise.taskmate.domain.database.Task
import com.howtokaise.taskmate.domain.database.TaskRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewmodel(private val repository: TaskRepository) : ViewModel() {
    val task: StateFlow<List<Task>> = repository.getAllTasks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val sortedTask = task.map {
        it.sortedBy { task -> task.isCompleted } }.distinctUntilChanged().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addTask(title : String){
        viewModelScope.launch {
            repository.insertTask(Task(title = title))
        }
    }

    fun updateTask(task: Task){
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }

}