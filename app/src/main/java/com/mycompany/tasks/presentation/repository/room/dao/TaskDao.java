package com.mycompany.tasks.presentation.repository.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.mycompany.tasks.presentation.repository.dto.TaskDto;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTask(TaskDto task);

    @Query("SELECT * FROM tasks ORDER BY id ASC")
    LiveData<List<TaskDto>> getAllTasks();
}
