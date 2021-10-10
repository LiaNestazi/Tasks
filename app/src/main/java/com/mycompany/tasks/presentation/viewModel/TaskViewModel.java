package com.mycompany.tasks.presentation.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.mycompany.tasks.models.Task;
import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.repository.room.TaskRepository;
import com.mycompany.tasks.presentation.repository.room.TaskRoomDatabase;
import com.mycompany.tasks.presentation.repository.room.dao.TaskDao;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    public LiveData<List<TaskDto>> getAllTasks;
    private TaskRepository repository;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        repository = new TaskRepository(TaskRoomDatabase.getDatabase(application).taskDao());
        getAllTasks = repository.getAllTasks;

    }

    public void addTask(TaskDto task){
        repository.addTask(task);
    }


}
