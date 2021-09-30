package com.touhid.technicalassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.touhid.technicalassignment.models.UserModel;
import com.touhid.technicalassignment.models.UserRepository;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<UserModel> user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public void insert(UserModel userModel){userRepository.insert(userModel);}

    public LiveData<UserModel> getUser (String email, String password){
        return userRepository.getUser(email, password);
    }
}
