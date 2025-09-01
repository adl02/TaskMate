package com.howtokaise.taskmate.domain.database

class TaskRepository(private val dao : TaskDao) {
    fun getAllTasks() = dao.getAllTasks()
    suspend fun insertTask(task: Task) = dao.insertTask(task)
    suspend fun updateTask(task: Task) = dao.updateTask(task)
    suspend fun deleteTask(task: Task) = dao.deleteTask(task)
}