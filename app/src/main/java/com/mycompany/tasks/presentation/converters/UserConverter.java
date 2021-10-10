package com.mycompany.tasks.presentation.converters;

import androidx.room.TypeConverter;

import com.mycompany.tasks.models.User;


public class UserConverter {
    @TypeConverter
    public static User toUser(String login) {
        return login == null ? null : new User(login);
    }

    @TypeConverter
    public static String toLogin(User user) {
        return user == null ? null : user.getLogin();
    }
}
