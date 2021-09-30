package com.touhid.technicalassignment.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void insert(UserModel userModel);

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    LiveData<UserModel> getUser(String email, String password);

}
