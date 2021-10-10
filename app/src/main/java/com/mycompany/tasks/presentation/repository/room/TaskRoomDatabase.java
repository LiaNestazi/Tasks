package com.mycompany.tasks.presentation.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mycompany.tasks.presentation.repository.dto.TaskDto;
import com.mycompany.tasks.presentation.repository.room.dao.TaskDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {TaskDto.class}, version = 1, exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static volatile TaskRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static TaskRoomDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            TaskRoomDatabase.class,
                            "tasks_database"
                    ).allowMainThreadQueries().build();
                }
            }
        }
        return INSTANCE;
    }
}
