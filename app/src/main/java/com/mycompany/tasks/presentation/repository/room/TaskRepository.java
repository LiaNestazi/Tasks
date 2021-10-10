package com.mycompany.tasks.presentation.repository.room;

import androidx.lifecycle.LiveData;

import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.repository.room.dao.TaskDao;

import java.util.List;

public class TaskRepository {

    public LiveData<List<TaskDto>> getAllTasks;
    TaskDao taskDao;

    public TaskRepository(TaskDao taskDao){
        this.taskDao = taskDao;
        getAllTasks = taskDao.getAllTasks();
    }

    public void addTask(TaskDto task){
        taskDao.addTask(task);
    }

}
