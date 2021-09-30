package com.touhid.technicalassignment.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;

    public UserRepository(Application application) {
        Database database = Database.getInstance(application);
        userDao = database.userDao();
    }

    public LiveData<UserModel> getUser(String email, String password){
        LiveData<UserModel> user = userDao.getUser(email, password);
        return user;
    }

    public void insert(UserModel userModel){
        new InsertAsyncTask(userDao).execute(userModel);
    }

    private static class InsertAsyncTask extends AsyncTask<UserModel, Void, Void> {
        UserDao userDao;
        public InsertAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserModel... userModels) {
            userDao.insert(userModels[0]);
            return null;
        }
    }
}
