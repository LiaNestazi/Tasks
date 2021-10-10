package com.mycompany.tasks.presentation.repository.dto;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import androidx.versionedparcelable.VersionedParcelize;

import com.mycompany.tasks.models.Task;
import com.mycompany.tasks.models.User;
import com.mycompany.tasks.presentation.converters.DateConverter;
import com.mycompany.tasks.presentation.converters.UserConverter;

import java.util.Date;

@Entity(tableName = "tasks")
public class TaskDto implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String desc;
    @ColumnInfo
    @TypeConverters(DateConverter.class)
    private Date date;
    @ColumnInfo
    @TypeConverters(UserConverter.class)
    private User creator;
    @ColumnInfo
    private boolean done;
    @ColumnInfo
    private String creator_phone;

    public TaskDto(){

    }

    protected TaskDto(Parcel in) {
        id = in.readInt();
        name = in.readString();
        desc = in.readString();
        done = in.readByte() != 0;
    }

    public static final Creator<TaskDto> CREATOR = new Creator<TaskDto>() {
        @Override
        public TaskDto createFromParcel(Parcel in) {
            return new TaskDto(in);
        }

        @Override
        public TaskDto[] newArray(int size) {
            return new TaskDto[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Date getDate() {
        return date;
    }

    public User getCreator() {
        return creator;
    }
    public boolean isDone() {
        return done;
    }

    public String getCreator_phone() {
        return creator_phone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setCreator_phone(String creator_phone) {
        this.creator_phone = creator_phone;
    }

    public static TaskDto convertFromTask(Task task) {
        TaskDto dto = new TaskDto();

        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setDesc(task.getDesc());
        dto.setCreator(task.getCreator());
        dto.setDate(task.getDate());

        return dto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {String.valueOf(id), name, desc, date.toString(), creator.getLogin(), String.valueOf(done)});

    }
}
