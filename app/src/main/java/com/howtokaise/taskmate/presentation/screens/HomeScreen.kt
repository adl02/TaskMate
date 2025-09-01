package com.howtokaise.taskmate.presentation.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.howtokaise.taskmate.domain.database.Task
import com.howtokaise.taskmate.presentation.AppViewmodel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewmodel: AppViewmodel) {
    val tasks by viewmodel.sortedTask.collectAsState()
    val isDarkTheme = isSystemInDarkTheme()

    var showAddDailog by remember { mutableStateOf(false) }
    var showEditDailog by remember { mutableStateOf<Task?>(null) }
    var showDeleteDialog by remember { mutableStateOf<Task?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                contentColor = Color.White,
                containerColor = Color(0xFFFFC107),
                shape = CircleShape,
                onClick = { showAddDailog = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isDarkTheme) Color.Black else Color.White),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF2E7D32)),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Banner",
                        tint = Color.White,
                        modifier = Modifier.size(36.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            "Stay Productive!",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "Complete your tasks one by one ✅",
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                }
            }
            val listState = rememberLazyListState()
            LazyColumn (state = listState){
                items(tasks, key = { it.id }) { task ->

                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = {
                            if (it == SwipeToDismissBoxValue.EndToStart) {
                                showDeleteDialog = task
                                false
                            } else false
                        }
                    )

                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(end = 24.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(46.dp)
                                        .background(Color.Red, CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                        tint = Color.White
                                    )
                                }
                            }
                        }
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(if (isDarkTheme) Color.Black else Color.White)
                                .clickable { showEditDailog = task }
                                .padding(start = 8.dp, end = 8.dp, top = 4.dp, bottom = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = if (isDarkTheme) Color.DarkGray else Color.LightGray),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.alpha(if (task.isCompleted) 0.5f else 1f)
                            ) {
                                Checkbox(
                                    checked = task.isCompleted,
                                    onCheckedChange = {
                                        viewmodel.updateTask(task.copy(isCompleted = it))
                                    },
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape),
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = Color.Green,
                                        uncheckedColor = Color.Gray,
                                        checkmarkColor = Color.White
                                    )
                                )
                                Text(
                                    text = task.title,
                                    modifier = Modifier.weight(1f),
                                    textDecoration = if (task.isCompleted) {
                                        TextDecoration.LineThrough
                                    } else null
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    if (showAddDailog) {
        TaskDialog(
            title = "Add Task",
            initialText = "",
            onDismiss = { showAddDailog = false },
            onConfirm = {
                viewmodel.addTask(it)
                showAddDailog = false
            }
        )
    }

    showEditDailog?.let { task ->
        TaskDialog(
            title = "Edit Task",
            initialText = task.title,
            onDismiss = { showEditDailog = null },
            onConfirm = {
                viewmodel.updateTask(task.copy(title = it))
                showEditDailog = null
            }
        )
    }

    showDeleteDialog?.let { task ->
        AlertDialog(
            onDismissRequest = { showDeleteDialog = null },
            title = {},
            text = { Text("Are you sure want to delete ?") },
            confirmButton = {
                TextButton(onClick = {
                    viewmodel.deleteTask(task)
                    showDeleteDialog = null
                }) { Text("Delete") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = null }) {
                    Text("Cancel")
                }
            }
        )
    }
}
